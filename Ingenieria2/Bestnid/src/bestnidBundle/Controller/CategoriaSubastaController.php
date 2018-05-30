<?php

namespace bestnidBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use bestnidBundle\Entity\CategoriaSubasta;
use bestnidBundle\Form\CategoriaSubastaType;

/**
 * CategoriaSubasta controller.
 *
 * @Route("/categoria")
 */
class CategoriaSubastaController extends Controller
{

	private $categoriasPorPagina = 4;
	
    /**
     * Lists all CategoriaSubasta entities.
     *
     * @Route("/admin/listado/{paginaActual}", name="categoriasubasta")
     * @Method("GET")
     * @Template()
     */
    public function indexAction($paginaActual = 0)
    {	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:CategoriaSubasta');
			
		$qb = $repository->createQueryBuilder('cat');
		
		$qb->select('cat')
			->setFirstResult($paginaActual * $this->categoriasPorPagina)	
			->setMaxResults($this->categoriasPorPagina)
			->orderBy('cat.nombreURL','ASC');
			
		$entities = $qb->getQuery()->getResult();
		
		
		// --------------------- * --------------------
		
		$cantidadCategorias = count($this->getDoctrine()->getRepository('bestnidBundle:CategoriaSubasta')->findAll());		

        return array(
            'entities' => $entities,
			'paginaActual' => $paginaActual,
			'cantidadPaginas' => ceil($cantidadCategorias / $this->categoriasPorPagina),
        );
    }
    /**
     * Creates a new CategoriaSubasta entity.
     *
     * @Route("/admin/new", name="categoriasubasta_create")
     * @Method("POST")
     * @Template("bestnidBundle:CategoriaSubasta:new.html.twig")
     */
    public function createAction(Request $request)
    {
        $entity = new CategoriaSubasta();
        $form = $this->createCreateForm($entity);
        $form->handleRequest($request);

        if ($form->isValid()) {
			try{
				$em = $this->getDoctrine()->getManager();
				$em->persist($entity);
				$em->flush();
			
			}
			
			catch(\Exception $e){
				
				return $this->redirect($this->generateUrl('categoriasubasta_new', array('msg' => 1)));
				
			}

            return $this->redirect($this->generateUrl('categoriasubasta', array('id' => $entity->getId())));
        }

        return array(
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Creates a form to create a CategoriaSubasta entity.
     *
     * @param CategoriaSubasta $entity The entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createCreateForm(CategoriaSubasta $entity)
    {
        $form = $this->createForm(new CategoriaSubastaType($this->getCategoriaSubastasPadres()), $entity, array(
            'action' => $this->generateUrl('categoriasubasta_create'),
            'method' => 'POST',
        ));

        $form->add('submit', 'submit', array('label' => 'Enviar', 'attr' => ['class' => 'btn btn-primary btn-block']));

        return $form;
    }

    /**
     * Displays a form to create a new CategoriaSubasta entity.
     *
     * @Route("/admin/new/{msg}", name="categoriasubasta_new")
     * @Method("GET")
     * @Template()
     */
    public function newAction($msg = 0)
    {
        $entity = new CategoriaSubasta();
        $form   = $this->createCreateForm($entity);

        return array(
			'msg' => $msg,
            'entity' => $entity,
            'form'   => $form->createView(),
        );
    }

    /**
     * Finds and displays a CategoriaSubasta entity.
     *
     * @Route("/{id}", name="listarCategoriaSubasta")
     * @Method("GET")
     * @Template()
     */
    public function listarCategoriaSubastaAction($id)
    {
        $em = $this->getDoctrine()->getManager();
		
		$repositorio = $em->getRepository('bestnidBundle:CategoriaSubasta');

		$CategoriaSubasta = $repositorio->findOneBy(['nombreURL' => $id]);
		
		if (!$CategoriaSubasta) {
            throw $this->createNotFoundException('Unable to find CategoriaSubasta entity.');
        }
		
		$CategoriasInternas = $repositorio->findBy(['idPadreCategoriaSubasta' => $CategoriaSubasta->getId()]);
		
		return $this->redirect($this->generateUrl('listarSubastas', array('CategoriaURL' => $CategoriaSubasta->getNombreURL(), 'pagina' => 0)));
		
    }

    /**
     * Displays a form to edit an existing CategoriaSubasta entity.
     *
     * @Route("/admin/{id}/edit/{msg}", name="categoriasubasta_edit")
     * @Method("GET")
     * @Template()
     */
    public function editAction($id, $msg = 0)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:CategoriaSubasta')->findOneBy(['id' => $id]);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find CategoriaSubasta entity.');
        }

        $editForm = $this->createEditForm($entity);
        $deleteForm = $this->createDeleteForm($id);

        return array(
			'msg' => $msg,
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }

    /**
    * Creates a form to edit a CategoriaSubasta entity.
    *
    * @param CategoriaSubasta $entity The entity
    *
    * @return \Symfony\Component\Form\Form The form
    */
    private function createEditForm(CategoriaSubasta $entity)
    {
        $form = $this->createForm(new CategoriaSubastaType($this->getCategoriaSubastasPadres($entity->getId())), $entity, array(
            'action' => $this->generateUrl('categoriasubasta_update', array('id' => $entity->getId())),
            'method' => 'PUT',
        ));

        $form->add('submit', 'submit', array('label' => 'Actualizar','attr' => array('class' => 'btn btn-large btn-warning btn-block') ));

        return $form;
    }
    /**
     * Edits an existing CategoriaSubasta entity.
     *
     * @Route("/admin/{id}", name="categoriasubasta_update")
     * @Method("PUT")
     * @Template("bestnidBundle:CategoriaSubasta:edit.html.twig")
     */
    public function updateAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:CategoriaSubasta')->find($id);

        if (!$entity) {
            throw $this->createNotFoundException('Unable to find CategoriaSubasta entity.');
        }

        $deleteForm = $this->createDeleteForm($id);
        $editForm = $this->createEditForm($entity);
        $editForm->handleRequest($request);

        if ($editForm->isValid()) {
			
			
			try{
					
				$em->flush();
					
			}
			catch(\Exception $e){ // ya existe el nombre
				
				return $this->redirect($this->generateUrl('categoriasubasta_edit', array('id' => $id,'msg' => 1)));
					
			}
			return $this->redirect($this->generateUrl('categoriasubasta'));
				
        }

        return array(
            'entity'      => $entity,
            'edit_form'   => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        );
    }
    /**
     * Deletes a CategoriaSubasta entity.
     *
     * @Route("/admin/{id}", name="categoriasubasta_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, $id)
    {
        $form = $this->createDeleteForm($id);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $entity = $em->getRepository('bestnidBundle:CategoriaSubasta')->find($id);

            if (!$entity) {
                throw $this->createNotFoundException('Unable to find CategoriaSubasta entity.');
            }
			try{
				
				$em->remove($entity);{}
				$em->flush();
				
			} catch(\Exception $e){ return $this->redirect($this->generateUrl('categoriasubasta_edit',[ 'id' => $id, 'msg' => 2])); }
        }

        return $this->redirect($this->generateUrl('categoriasubasta'));
    }

    /**
     * Creates a form to delete a CategoriaSubasta entity by id.
     *
     * @param mixed $id The entity id
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm($id)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('categoriasubasta_delete', array('id' => $id)))
            ->setMethod('DELETE')
            ->add('submit', 'submit', array('label' => 'Eliminar','attr' => array('class' => 'btn btn-large btn-danger btn-block') ))
            ->getForm()
        ;
    }
	
	public function getCategoriaSubastasPadres($idCat = -1){
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:CategoriaSubasta');
			
		$qb = $repository->createQueryBuilder('cat');
		
		$qb->select('cat');
		
		if ($idCat == -1){
		
			$qb->andWhere('cat.idPadreCategoriaSubasta IS NULL');
			
		}else{
			
		$qb->andWhere('cat.idPadreCategoriaSubasta IS NULL and cat.id <> :idCat')
			->setParameter(':idCat',$idCat);
		}
			
		return $qb->getQuery()->getResult();
		
	}
	
}
