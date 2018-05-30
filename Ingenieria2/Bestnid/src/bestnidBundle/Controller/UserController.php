<?php

namespace bestnidBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use bestnidBundle\Entity\Respuesta;
use Symfony\Component\HttpFoundation\Request;
use bestnidBundle\Form\RespuestaType;

class UserController extends Controller
{
	/**
     * @Route("/user/", name="miCuenta")
     * @Template()
     */
    public function indexAction()
    {
		$preguntas = null;
		$r = null;
		
        return $this->render("bestnidBundle:User:index.html.twig", array('cantPreguntas' => count($this->getPreguntasSinResponder() ), 'Notificaciones' => $this->getNotificaciones()));
    }
	
	/**
     * @Route("/user/suspender", name="suspender_usuario")
     * @Template()
     */
    public function suspenderUsuarioAction()
    {
		
		$post = Request::createFromGlobals();
 
		
		$user = $post->request->get('user');
		
		$em = $this->getDoctrine()->getManager();

        $entity = $em->getRepository('bestnidBundle:User')->findOneByUsername($user);
		
		
		
		if (!$entity) {
            return $this->redirect($this->generateUrl('admin', array('msg' => '2')));
        }
		
		$entity->setLocked(1);
		
		 $em->flush();
		
		return $this->redirect($this->generateUrl('admin', array('msg' => '1')));
       
    }
	
	/**
     * @Route("/user/preguntas", name="preguntas")
     * @Template()
     */
    public function preguntasAction()
    {
		$preguntas = null;
		$r = null;
		
		foreach ($this->getPreguntasSinResponder() as $p){
			
			$r = new Respuesta;
			
			$a['form'] = $this->createRespuestaCreateForm($r)->createView();
			$a['delete_form'] = $this->createRespuestaDeleteForm($p->getId())->createView();
			$a['p'] = $p;
			
			$preguntas[] = $a;
			
		}
		
        return $this->render("bestnidBundle:User:preguntas.html.twig", array('cantPreguntas' => count($preguntas), 'preguntas' => $preguntas ));
    }
	
	private function getNotificaciones(){
		
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Oferta');
			
		$qb = $repository->createQueryBuilder('o');
		
		$qb->select('o');
		
		$qb->join('o.subasta', 's');
		
		$qb->andWhere('o.usuario = :idUser');
		
		$qb->andWhere('o.ganadora = 1');
		
		$qb->andWhere('s.estado = 1');

		$qb->setParameter(':idUser',$this->getUser()->getId());
			
		return $qb->getQuery()->getResult();
	}
	
	private function createRespuestaCreateForm(Respuesta $entity)
    {
        $form = $this->createForm(new RespuestaType(), $entity, array(
            'action' => $this->generateUrl('respuesta_create'),
            'method' => 'POST',
        ));

        $form->add('submit', 'submit', array('label' => 'Responder','attr' => array('class' => 'btn btn-large btn-primary') ));

        return $form;
    }
	
	private function createRespuestaDeleteForm($id)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('pregunta_delete', array('id' => $id)))
            ->setMethod('DELETE')
            ->add('submit', 'submit', array('label' => 'Eliminar','attr' => array('class' => 'btn btn-large btn-danger') ))
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
