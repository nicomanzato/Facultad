<?php

namespace bestnidBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use bestnidBundle\Entity\Venta;
use bestnidBundle\Form\VentaType;

/**
 * Venta controller.
 *
 * @Route("/venta")
 */
class VentaController extends Controller
{

    /**
     * Lists all Venta entities.
     *
     * @Route("/", name="venta")
     * @Method("GET")
     * @Template()
     */
    public function indexAction()
    {
        return array(
			'cantPreguntas' => count($this->getPreguntasSinResponder() ),
            'entities' => $this->getComprasRealizadas(),
        );
    }
	
	/**
     * Lists all Venta entities.
     *
     * @Route("/productosVendidos", name="productosVendidos")
     * @Method("GET")
     * @Template()
     */
    public function misVentasAction()
    {
        return array(
			'cantPreguntas' => count($this->getPreguntasSinResponder() ),
            'entities' => $this->getVentasRealizadas(),
        );
    }
	
	public function getVentasRealizadas(){
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Venta');
			
		$qb = $repository->createQueryBuilder('venta');
		
		$qb->select('venta');
		
		$qb->join('venta.oferta', 'oferta');
		
		$qb->join('oferta.subasta', 'subasta');
		
		$qb->andWhere('subasta.idUserSubastador = :id');
		
		$qb->andWhere('subasta.estado = 2');

		$qb->setParameter(':id',$this->getUser()->getId());
			
		return $qb->getQuery()->getResult();
		
	}
	
	public function getComprasRealizadas(){
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Venta');
			
		$qb = $repository->createQueryBuilder('venta');
		
		$qb->select('venta');
		
		$qb->join('venta.oferta', 'oferta');
		
		$qb->join('oferta.subasta', 'subasta');
		
		$qb->andWhere('oferta.usuario = :id');
		
		$qb->andWhere('subasta.estado = 2');

		$qb->setParameter(':id',$this->getUser()->getId());
			
		return $qb->getQuery()->getResult();
		
	}
    /**
     * Creates a new Venta entity.
     *
     * @Route("/", name="venta_create")
     * @Method("POST")
     * @Template("bestnidBundle:Venta:new.html.twig")
     */
    public function createAction(Request $request)
    {
        $entity = new Venta();
        $form = $this->createCreateForm($entity);
        $form->handleRequest($request);

        if ($form->isValid()) {
			
			
			$entity->setTarjeta($this->getDoctrine()->getManager()->getRepository('bestnidBundle:Tarjeta')->findOneById($form->getData()->getTarjeta()->getId()));
			
			$entity->setOferta($this->getDoctrine()->getManager()->getRepository('bestnidBundle:Oferta')->findOneById($form->getData()->getOferta()));
			
			$entity->setPorcentajeVenta($this->getDoctrine()->getManager()->getRepository('bestnidBundle:Configuracion')->findOneByNombre('porcentajeVenta')->getValor());
			
			$subasta = $entity->getOferta()->getSubasta();
			
			$subasta->setEstado(2);
			
			$entity->setFecha(new \Datetime());
			
            $em = $this->getDoctrine()->getManager();
            
			$em->persist($entity);
			$em->persist($subasta);
			$em->flush();

            return $this->redirect($this->generateUrl('miCuenta', array('id' => $entity->getId())));
			
        }

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Creates a form to create a Venta entity.
     *
     * @param Venta $entity The entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createCreateForm(Venta $entity)
    {
        $form = $this->createForm(new VentaType($this->getDoctrine()->getManager()->getRepository('bestnidBundle:Tarjeta')->findByUsuario($this->getUser()->getId())), $entity, array(
            'action' => $this->generateUrl('venta_create'),
            'method' => 'POST',
        ));

        $form->add('submit', 'submit', array('label' => 'Enviar','attr' => array('class' => 'btn btn-large btn-primary') ));

        return $form;
    }

    /**
     * Displays a form to create a new Venta entity.
     *
     * @Route("/new/{idOferta}", name="venta_new")
     * @Method("GET")
     * @Template()
     */
    public function newAction($idOferta)
    {
		$oferta = $this->getDoctrine()->getManager()->getRepository('bestnidBundle:Oferta')->findOneById($idOferta);
        $entity = new Venta();
		$entity->setOferta($oferta->getId());
        $form   = $this->createCreateForm($entity);

        return array(
			'ofertaGanadora' => $oferta,
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Finds and displays a Venta entity.
     *
     * @Route("/{id}", name="venta_show")
     * @Method("GET")
     * @Template()
     */
    public function showAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Venta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Venta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);

        return array(
            'entity'      => $entity,
            'delete_form' => $deleteForm->createView(),
        );
    }

    /**
     * Displays a form to edit an existing Venta entity.
     *
     * @Route("/{id}/edit", name="venta_edit")
     * @Method("GET")
     * @Template()
     */
    public function editAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Venta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Venta entity.');
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
    * Creates a form to edit a Venta entity.
    *
    * @param Venta $entity The entity
    *
    * @return \Symfony\Component\Form\Form The form
    */
    private function createEditForm(Venta $entity)
    {
        $form = $this->createForm(new VentaType(), $entity, array(
            'action' => $this->generateUrl('venta_update', array('id' => $entity->getId())),
            'method' => 'PUT',
        ));

        $form->add('submit', 'submit', array('label' => 'Update'));

        return $form;
    }
    /**
     * Edits an existing Venta entity.
     *
     * @Route("/{id}", name="venta_update")
     * @Method("PUT")
     * @Template("bestnidBundle:Venta:edit.html.twig")
     */
    public function updateAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:Venta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find Venta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);
        $editForm = $this->createEditForm($entity);
        $editForm->handleRequest($request);

        if ($editForm->isValid()) {
            $em->flush();

            return $this->redirect($this->generateUrl('venta_edit', array('id' => $id)));
        }

        return array(
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }
    /**
     * Deletes a Venta entity.
     *
     * @Route("/{id}", name="venta_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, $id)
    {
        $form = $this->createDeleteForm($id);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $entity = $em->getRepository('bestnidBundle:Venta')->find($id);

            if (!$entity) {
                throw $this->createNotFoundException('Unable to find Venta entity.');
            }

            $em->remove($entity);
            $em->flush();
        }

        return $this->redirect($this->generateUrl('venta'));
    }

    /**
     * Creates a form to delete a Venta entity by id.
     *
     * @param mixed $id The entity id
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm($id)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('venta_delete', array('id' => $id)))
            ->setMethod('DELETE')
            ->add('submit', 'submit', array('label' => 'Delete'))
            ->getForm()
        ;
    }
	public function getPreguntasSinResponder(){
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Pregunta');
			
		$qb = $repository->createQueryBuilder('p');
		
		$qb->select('p');
		
		$qb->join('p.subasta', 's');
		
		$qb->andWhere('s.idUserSubastador = :id');
		
		$qb->andWhere('p.respuesta IS NULL');

		$qb->setParameter(':id',$this->getUser()->getId());
			
		return $qb->getQuery()->getResult();
		
	}
}
