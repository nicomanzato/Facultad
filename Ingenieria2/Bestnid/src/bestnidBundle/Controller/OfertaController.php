<?php

namespace bestnidBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use bestnidBundle\Entity\Oferta;
use bestnidBundle\Form\OfertaType;

/**
 * Oferta controller.
 *
 * @Route("/oferta")
 */
class OfertaController extends Controller
{
	private $ofertasPorPagina = 4;

    /**
     * Lists all Oferta entities.
     *
     * @Route("/{paginaActual}", name="ofertasUsuario")
     * @Method("GET")
     * @Template()
     */
    public function misOfertasAction($paginaActual = 0)
    {
        $entities = $this->getOfertasWithUser($paginaActual);

		$ofertas = null;
		
		$a = null;
		
		foreach($entities as $entity){
			
			$a['oferta'] = $entity;
			$a['delete_form'] = $this->createDeleteForm($entity->getId())->createView();
			
			$ofertas[] = $a;
			
		}
		
        return array(
            'ofertas' => $ofertas,
			'cantidadPaginas' => ceil($this->countOfertasWithUser() / $this->ofertasPorPagina),
			'paginaActual' => $paginaActual,
        );
    }
    /**
     * Creates a new Oferta entity.
     *
     * @Route("/", name="oferta_create")
     * @Method("POST")
     * @Template("bestnidBundle:Oferta:new.html.twig")
     */
    public function createAction(Request $request)
    {
        $entity = new Oferta();
        $form = $this->createCreateForm($entity);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
			
			$entity->setUsuario($this->getDoctrine()->getManager()->getRepository('bestnidBundle:User')->findOneById($this->getUser()->getId()));
			$entity->setSubasta($this->getDoctrine()->getManager()->getRepository('bestnidBundle:Subasta')->findOneById($form->getData()->getSubasta()));
			$entity->setFecha(new \DateTime());
			$entity->setGanadora(false);
			
			
            $em->persist($entity);
            $em->flush();

            return $this->redirect($this->generateUrl('subasta_show', array('id' => $entity->getSubasta()->getId(), 'msg' => 1)));
        }

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Creates a form to create a Oferta entity.
     *
     * @param Oferta $entity The entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createCreateForm(Oferta $entity)
    {
        $form = $this->createForm(new OfertaType(), $entity, array(
            'action' => $this->generateUrl('oferta_create'),
            'method' => 'POST',
        ));

        $form->add('submit', 'submit', array('label' => 'Enviar','attr' => array('class' => 'btn btn-large btn-primary') ));

        return $form;
    }

    /**
     * Displays a form to create a new Oferta entity.
     *
     * @Route("/new/{idSubasta}", name="oferta_new")
     * @Method("GET")
     * @Template()
     */
    public function newAction($idSubasta)
    {
		$subasta = $this->getDoctrine()->getManager()->getRepository('bestnidBundle:Subasta')->findOneById($idSubasta);
		
		foreach ($this->getDoctrine()->getManager()->getRepository('bestnidBundle:Oferta')->findBySubasta($idSubasta) as $oferta){
			
			if ($oferta->getUsuario()->getId() == $this->getUser()->getId()){ 
			
				return $this->redirect($this->generateUrl('subasta_show',array('id' => $oferta->getSubasta()->getId(), 'msg' => 2))); 
				
			}
			
		}
		
		if ($subasta->getIdUserSubastador()->getId() == $this->getUser()->getId()){ return $this->redirect($this->generateUrl('subastaDueno')); }
		
		if (!$subasta->estaFinalizada()){
			 
			$entity = new Oferta();
			$entity->setSubasta($subasta->getId());
			$form   = $this->createCreateForm($entity);

			return array(
				'entity' => $entity,
				'form'   => $form->createView(),
			);
		}else{
			
			return $this->redirect($this->generateUrl('subastaFinalizada'));
			
		}
	
    }

    /**
     * Finds and displays a Oferta entity.
     *
     * @Route("/{id}", name="oferta_show")
     * @Method("GET")
     * @Template()
     */
    public function showAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Oferta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Oferta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);

        return array(
            'entity'      => $entity,
            'delete_form' => $deleteForm->createView(),
        );
    }

    /**
     * Displays a form to edit an existing Oferta entity.
     *
     * @Route("/{id}/edit", name="oferta_edit")
     * @Method("GET")
     * @Template()
     */
    public function editAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Oferta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Oferta entity.');
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
    * Creates a form to edit a Oferta entity.
    *
    * @param Oferta $entity The entity
    *
    * @return \Symfony\Component\Form\Form The form
    */
    private function createEditForm(Oferta $entity)
    {
        $form = $this->createForm(new OfertaType(), $entity, array(
            'action' => $this->generateUrl('oferta_update', array('id' => $entity->getId())),
            'method' => 'PUT',
        ));

        $form->add('submit', 'submit', array('label' => 'Update'));

        return $form;
    }
    /**
     * Edits an existing Oferta entity.
     *
     * @Route("/{id}", name="oferta_update")
     * @Method("PUT")
     * @Template("bestnidBundle:Oferta:edit.html.twig")
     */
    public function updateAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Oferta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Oferta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);
        $editForm = $this->createEditForm($entity);
        $editForm->handleRequest($request);

        if ($editForm->isValid()) {
            $em->flush();

            return $this->redirect($this->generateUrl('oferta_edit', array('id' => $id)));
        }

        return array(
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }
    /**
     * Deletes a Oferta entity.
     *
     * @Route("/{id}", name="oferta_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, $id)
    {
        $form = $this->createDeleteForm($id);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $entity = $em->getRepository('bestnidBundle:Oferta')->find($id);

            if (!$entity) {
                throw $this->createNotFoundException('Unable to find Oferta entity.');
            }

            $em->remove($entity);
            $em->flush();
        }

        return $this->redirect($this->generateUrl('ofertasUsuario'));
    }

    /**
     * Creates a form to delete a Oferta entity by id.
     *
     * @param mixed $id The entity id
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm($id)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('oferta_delete', array('id' => $id)))
            ->setMethod('DELETE')
            ->add('submit', 'submit', array('label' => 'Eliminar','attr' => array('class' => 'btn btn-large btn-danger') ))
            ->getForm()
        ;
    }
	
	private function getOfertasWithUser($paginaActual = '0'){
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Oferta');
			
		$qb = $repository->createQueryBuilder('s');
		
		$qb->select('s')
			->setFirstResult($paginaActual * $this->ofertasPorPagina)	
			->setMaxResults($this->ofertasPorPagina)
			->orderBy('s.fecha','DESC');
		
			$qb->andWhere('s.usuario = :User');
			$qb->setParameter(':User',$this->getUser()->getId());	    
			
			
		return $qb->getQuery()->getResult();
	
	}
	
	private function countOfertasWithUser(){
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Oferta');
			
		$qb = $repository->createQueryBuilder('s');
		
		$qb->select('s');
		
		$qb->andWhere('s.usuario = :User');
		$qb->setParameter(':User',$this->getUser()->getId());	    
			
		return count($qb->getQuery()->getResult());
	
	}
	
	/**
     * Edits an existing Oferta entity.
     *
     * @Route("/ganadora/{id}", name="oferta_ganadora")
     */
    public function ganadoraAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Oferta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Oferta entity.');
        }
		
		foreach( $em->getRepository('bestnidBundle:Oferta')->findBySubasta($entity->getSubasta()->getId()) as $oferta){
			
			$oferta->setGanadora(false);
			
		}
		
		$entity->setGanadora(true);
		
		$entity->getSubasta()->setEstado(1);
		$entity->getSubasta()->setFechaOfertaGanadora(new \Datetime);
		
		
       
        $em->flush();

        return $this->redirect($this->generateUrl('subastasUsuario', array('id' => $id)));
    }
}
