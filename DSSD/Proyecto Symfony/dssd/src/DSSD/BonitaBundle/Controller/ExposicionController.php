<?php

namespace DSSD\BonitaBundle\Controller;

use DSSD\BonitaBundle\Entity\Exposicion;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Exposicion controller.
 *
 * @Route("exposicion")
 */
class ExposicionController extends Controller
{
    /**
     * Lists all exposicion entities.
     *
     * @Route("/", name="exposicion_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $exposicions = $em->getRepository('DSSDBonitaBundle:Exposicion')->findAll();

        return $this->render('exposicion/index.html.twig', array(
            'exposicions' => $exposicions,
        ));
    }

    /**
     * Creates a new exposicion entity.
     *
     * @Route("/new", name="exposicion_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $exposicion = new Exposicion();
        $form = $this->createForm('DSSD\BonitaBundle\Form\ExposicionType', $exposicion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($exposicion);
            $em->flush($exposicion);

            return $this->redirectToRoute('exposicion_show', array('id' => $exposicion->getId()));
        }

        return $this->render('exposicion/new.html.twig', array(
            'exposicion' => $exposicion,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a exposicion entity.
     *
     * @Route("/{id}", name="exposicion_show")
     * @Method("GET")
     */
    public function showAction(Exposicion $exposicion)
    {
        $deleteForm = $this->createDeleteForm($exposicion);

        return $this->render('exposicion/show.html.twig', array(
            'exposicion' => $exposicion,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing exposicion entity.
     *
     * @Route("/{id}/edit", name="exposicion_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Exposicion $exposicion)
    {
        $deleteForm = $this->createDeleteForm($exposicion);
        $editForm = $this->createForm('DSSD\BonitaBundle\Form\ExposicionType', $exposicion);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('exposicion_edit', array('id' => $exposicion->getId()));
        }

        return $this->render('exposicion/edit.html.twig', array(
            'exposicion' => $exposicion,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a exposicion entity.
     *
     * @Route("/{id}", name="exposicion_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Exposicion $exposicion)
    {
        $form = $this->createDeleteForm($exposicion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($exposicion);
            $em->flush($exposicion);
        }

        return $this->redirectToRoute('exposicion_index');
    }

    /**
     * Creates a form to delete a exposicion entity.
     *
     * @param Exposicion $exposicion The exposicion entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Exposicion $exposicion)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('exposicion_delete', array('id' => $exposicion->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
