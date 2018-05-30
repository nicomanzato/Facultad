<?php

namespace bestnidBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use bestnidBundle\Entity\Tarjeta;
use bestnidBundle\Form\TarjetaType;

/**
 * Tarjeta controller.
 *
 * @Route("/tarjeta")
 */
class TarjetaController extends Controller
{

    /**
     * Lists all Tarjeta entities.
     *
     * @Route("/", name="tarjeta")
     * @Method("GET")
     * @Template()
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $entities = $em->getRepository('bestnidBundle:Tarjeta')->findBy(['usuario' => $this->getUser()->getId()]);

        return array(
            'entities' => $entities,
        );
    }
    /**
     * Creates a new Tarjeta entity.
     *
     * @Route("/", name="tarjeta_create")
     * @Method("POST")
     * @Template("bestnidBundle:Tarjeta:new.html.twig")
     */
    public function createAction(Request $request)
    {
        $entity = new Tarjeta();
        $form = $this->createCreateForm($entity);
        $form->handleRequest($request);

        if ($form->isValid()) {
			$entity->setUsuario($this->getUser());
            $em = $this->getDoctrine()->getManager();
            $em->persist($entity);
            $em->flush();

            return $this->redirect($this->generateUrl('tarjeta', array('id' => $entity->getId())));
        }

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Creates a form to create a Tarjeta entity.
     *
     * @param Tarjeta $entity The entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createCreateForm(Tarjeta $entity)
    {
        $form = $this->createForm(new TarjetaType(), $entity, array(
            'action' => $this->generateUrl('tarjeta_create'),
            'method' => 'POST',
        ));

        $form->add('submit', 'submit', array('label' => 'Enviar','attr' => array('class' => 'btn btn-large btn-primary') ));

        return $form;
    }

    /**
     * Displays a form to create a new Tarjeta entity.
     *
     * @Route("/new", name="tarjeta_new")
     * @Method("GET")
     * @Template()
     */
    public function newAction()
    {
        $entity = new Tarjeta();
        $form   = $this->createCreateForm($entity);

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Finds and displays a Tarjeta entity.
     *
     * @Route("/{id}", name="tarjeta_show")
     * @Method("GET")
     * @Template()
     */
    public function showAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Tarjeta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Tarjeta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);

        return array(
            'entity'      => $entity,
            'delete_form' => $deleteForm->createView(),
        );
    }

    /**
     * Displays a form to edit an existing Tarjeta entity.
     *
     * @Route("/{id}/edit", name="tarjeta_edit")
     * @Method("GET")
     * @Template()
     */
    public function editAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Tarjeta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Tarjeta entity.');
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
    * Creates a form to edit a Tarjeta entity.
    *
    * @param Tarjeta $entity The entity
    *
    * @return \Symfony\Component\Form\Form The form
    */
    private function createEditForm(Tarjeta $entity)
    {
        $form = $this->createForm(new TarjetaType(), $entity, array(
            'action' => $this->generateUrl('tarjeta_update', array('id' => $entity->getId())),
            'method' => 'PUT',
        ));

        $form->add('submit', 'submit', array('label' => 'Actualizar','attr' => array('class' => 'btn btn-large btn-warning btn-block') ));

        return $form;
    }
    /**
     * Edits an existing Tarjeta entity.
     *
     * @Route("/{id}", name="tarjeta_update")
     * @Method("PUT")
     * @Template("bestnidBundle:Tarjeta:edit.html.twig")
     */
    public function updateAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Tarjeta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Tarjeta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);
        $editForm = $this->createEditForm($entity);
        $editForm->handleRequest($request);

        if ($editForm->isValid()) {
            $em->flush();

            return $this->redirect($this->generateUrl('tarjeta_edit', array('id' => $id)));
        }

        return array(
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }
    /**
     * Deletes a Tarjeta entity.
     *
     * @Route("/{id}", name="tarjeta_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, $id)
    {
        $form = $this->createDeleteForm($id);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $entity = $em->getRepository('bestnidBundle:Tarjeta')->find($id);

            if (!$entity) {
                throw $this->createNotFoundException('Unable to find Tarjeta entity.');
            }

            $em->remove($entity);
            $em->flush();
        }

        return $this->redirect($this->generateUrl('tarjeta'));
    }

    /**
     * Creates a form to delete a Tarjeta entity by id.
     *
     * @param mixed $id The entity id
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm($id)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('tarjeta_delete', array('id' => $id)))
            ->setMethod('DELETE')
            ->add('submit', 'submit', array('label' => 'Eliminar','attr' => array('class' => 'btn btn-large btn-danger btn-block') ))
            ->getForm()
        ;
    }
}
