<?php

namespace bestnidBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use bestnidBundle\Entity\Configuracion;
use bestnidBundle\Form\ConfiguracionType;

/**
 * Configuracion controller.
 *
 * @Route("/configuracion")
 */
class ConfiguracionController extends Controller
{

    /**
     * Lists all Configuracion entities.
     *
     * @Route("/", name="configuracion")
     * @Method("GET")
     * @Template()
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $entities = $em->getRepository('bestnidBundle:Configuracion')->findAll();

        return array(
            'entities' => $entities,
        );
    }
    /**
     * Creates a new Configuracion entity.
     *
     * @Route("/", name="configuracion_create")
     * @Method("POST")
     * @Template("bestnidBundle:Configuracion:new.html.twig")
     */
    public function createAction(Request $request)
    {
        $entity = new Configuracion();
        $form = $this->createCreateForm($entity);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($entity);
            $em->flush();

            return $this->redirect($this->generateUrl('configuracion_show', array('id' => $entity->getId())));
        }

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Creates a form to create a Configuracion entity.
     *
     * @param Configuracion $entity The entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createCreateForm(Configuracion $entity)
    {
        $form = $this->createForm(new ConfiguracionType(), $entity, array(
            'action' => $this->generateUrl('configuracion_create'),
            'method' => 'POST',
        ));

        $form->add('submit', 'submit', array('label' => 'Create'));

        return $form;
    }

    /**
     * Displays a form to create a new Configuracion entity.
     *
     * @Route("/new", name="configuracion_new")
     * @Method("GET")
     * @Template()
     */
    public function newAction()
    {
        $entity = new Configuracion();
        $form   = $this->createCreateForm($entity);

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Finds and displays a Configuracion entity.
     *
     * @Route("/{id}", name="configuracion_show")
     * @Method("GET")
     * @Template()
     */
    public function showAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Configuracion')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Configuracion entity.');
        }

        $deleteForm = $this->createDeleteForm($id);

        return array(
            'entity'      => $entity,
            'delete_form' => $deleteForm->createView(),
        );
    }

    /**
     * Displays a form to edit an existing Configuracion entity.
     *
     * @Route("/{id}/edit", name="configuracion_edit")
     * @Method("GET")
     * @Template()
     */
    public function editAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Configuracion')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Configuracion entity.');
        }

        $editForm = $this->createEditForm($entity);
        $deleteForm = $this->createDeleteForm($id);

        return array(
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }

    /**
    * Creates a form to edit a Configuracion entity.
    *
    * @param Configuracion $entity The entity
    *
    * @return \Symfony\Component\Form\Form The form
    */
    private function createEditForm(Configuracion $entity)
    {
        $form = $this->createForm(new ConfiguracionType(), $entity, array(
            'action' => $this->generateUrl('configuracion_update', array('id' => $entity->getId())),
            'method' => 'PUT',
        ));

        $form->add('submit', 'submit', array('label' => 'Actualizar', 'attr' => ['class' => 'btn btn-primary btn-block']));

        return $form;
    }
    /**
     * Edits an existing Configuracion entity.
     *
     * @Route("/{id}", name="configuracion_update")
     * @Method("PUT")
     * @Template("bestnidBundle:Configuracion:edit.html.twig")
     */
    public function updateAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Configuracion')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Configuracion entity.');
        }

        $deleteForm = $this->createDeleteForm($id);
        $editForm = $this->createEditForm($entity);
        $editForm->handleRequest($request);

        if ($editForm->isValid()) {
            $em->flush();

            return $this->redirect($this->generateUrl('configuracion_edit', array('id' => $id)));
        }

        return array(
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }
    /**
     * Deletes a Configuracion entity.
     *
     * @Route("/{id}", name="configuracion_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, $id)
    {
        $form = $this->createDeleteForm($id);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $entity = $em->getRepository('bestnidBundle:Configuracion')->find($id);

            if (!$entity) {
                throw $this->createNotFoundException('Unable to find Configuracion entity.');
            }

            $em->remove($entity);
            $em->flush();
        }

        return $this->redirect($this->generateUrl('configuracion'));
    }

    /**
     * Creates a form to delete a Configuracion entity by id.
     *
     * @param mixed $id The entity id
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm($id)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('configuracion_delete', array('id' => $id)))
            ->setMethod('DELETE')
            ->add('submit', 'submit', array('label' => 'Delete'))
            ->getForm()
        ;
    }
}
