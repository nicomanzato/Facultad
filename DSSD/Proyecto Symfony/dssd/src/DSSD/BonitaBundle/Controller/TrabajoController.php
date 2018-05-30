<?php

namespace DSSD\BonitaBundle\Controller;

use DSSD\BonitaBundle\Entity\Trabajo;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * Trabajo controller.
 *
 * @Route("trabajo")
 */
class TrabajoController extends Controller
{
    /**
     * Lists all trabajo entities.
     *
     * @Route("/", name="trabajo_index")
     * @Method("GET")
     */
    public function indexAction()
    {

        $em = $this->getDoctrine()->getManager();

        $trabajos = $em->getRepository('DSSDBonitaBundle:Trabajo')->findAll();

        return $this->render('trabajo/index.html.twig', array(
            'trabajos' => $trabajos,
        ));

    }

    /**
     * Lists all trabajo entities.
     *
     * @Route("/generate_book", name="generate_book")
     * @Method("GET")
     */
    public function generateBookAction()
    {

        $em = $this->getDoctrine()->getManager();

        $trabajos = $em->getRepository('DSSDBonitaBundle:Trabajo')->findAll();

        $html = $this->render('trabajo/book_generator.html.twig', array(
            'trabajos' => $trabajos,
        ));

        return new Response(
            $this->get('knp_snappy.pdf')->getOutputFromHtml($html),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'attachment; filename="file.pdf"'
            )
        );

    }

    /**
     * Marca como finalizado a un trabajo, el cual viene especificado el id por parametro get
     *
     * @Route("/finalizarTrabajo", name="finalizarTrabajo")
     * @Method("GET")
     */
    public function finalizarTrabajoAction(Request $request)
    {

        $idTrabajo = $request->query->get('idTrabajo');

        $em = $this->getDoctrine()->getManager();

        $trabajo = $em->getRepository('DSSDBonitaBundle:Trabajo')->findOneById($idTrabajo);

        $trabajo->setFinalizado(true);

        $em->persist($trabajo);
        $em->flush($trabajo);

        return $this->render('trabajo/trabajoFinalizado.html.twig', array());
    }


    /**
     * Creates a new trabajo entity.
     *
     * @Route("/new", name="trabajo_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $trabajo = new Trabajo();
        $form = $this->createForm('DSSD\BonitaBundle\Form\TrabajoType', $trabajo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($trabajo);
            $em->flush($trabajo);

            return $this->redirectToRoute('trabajo_show', array('id' => $trabajo->getId()));
        }

        return $this->render('trabajo/new.html.twig', array(
            'trabajo' => $trabajo,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a trabajo entity.
     *
     * @Route("/{id}", name="trabajo_show")
     * @Method("GET")
     */
    public function showAction(Trabajo $trabajo)
    {
        $deleteForm = $this->createDeleteForm($trabajo);

        return $this->render('trabajo/show.html.twig', array(
            'trabajo' => $trabajo,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing trabajo entity.
     *
     * @Route("/{id}/edit", name="trabajo_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Trabajo $trabajo)
    {
        $deleteForm = $this->createDeleteForm($trabajo);
        $editForm = $this->createForm('DSSD\BonitaBundle\Form\TrabajoType', $trabajo);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('trabajo_edit', array('id' => $trabajo->getId()));
        }

        return $this->render('trabajo/edit.html.twig', array(
            'trabajo' => $trabajo,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a trabajo entity.
     *
     * @Route("/{id}", name="trabajo_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Trabajo $trabajo)
    {
        $form = $this->createDeleteForm($trabajo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($trabajo);
            $em->flush($trabajo);
        }

        return $this->redirectToRoute('trabajo_index');
    }

    /**
     * Creates a form to delete a trabajo entity.
     *
     * @param Trabajo $trabajo The trabajo entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Trabajo $trabajo)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('trabajo_delete', array('id' => $trabajo->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


}
