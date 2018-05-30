<?php

namespace bestnidBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Session\Session;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;

class DefaultController extends Controller
{
	
    /**
     * @Route("/", name="inicio")
     * @Template()
     */
    public function indexAction()
    {
		
        return $this->render("bestnidBundle:Default:index.html.twig",['UltimasSubastas' => $this->getUltimasSubastas(),'CategoriaSubastas' => $this->getCategoriaSubastas()]);
    }
	
	/**
     * @Route("/faq", name="faq")
     * @Template()
     */
    public function faqAction()
    {
		
        return $this->render("bestnidBundle:Default:faq.html.twig");
    }
	
	/**
     * @Route("/ganancias", name="ganancias")
     * @Template()
     */
    public function gananciasAction()
    {
		
		$fechaInicio = $_GET['fechaInicio'];
		
		$fechaFin = $_GET['fechaFin'];
	
		$repository = $this->getDoctrine()->getRepository('bestnidBundle:Venta');
			
		$qb = $repository->createQueryBuilder('s');
		
		$qb->select('s')
			->where('s.fecha BETWEEN :fechaInicio AND :fechaFin')
			->setParameter(':fechaInicio',$fechaInicio)
			->setParameter(':fechaFin',$fechaFin);
			
		$ventas = $qb->getQuery()->getResult();
		
		$gananciaTotal = 0;
		
		foreach($ventas as $v){
			
			$gananciaTotal += ($v->getPorcentajeVenta()) * $v->getOferta()->getMonto();
			
		}
		
        return $this->render("bestnidBundle:Default:ganancias.html.twig",['gananciaTotal' => $gananciaTotal, 'ventas' => $ventas, 'fechaInicio' => $fechaInicio, 'fechaFin' => $fechaFin]);
    }
	
	private function getUltimasSubastas(){
		
		$em = $this->getDoctrine()->getManager();

        $repositorio = $em->getRepository('bestnidBundle:Subasta');
		
		$qb = $repositorio->createQueryBuilder('s');
		
		return $qb->select('s')->andWhere('(s.fechaVencimiento > :now AND s.estado = 0)')->setParameter('now', new \DateTime('now'))->orderBy('s.fechaPublicacion','DESC')->setMaxResults('4')->getQuery()->getResult();
		
	}
	
	private function getCategoriaSubastas(){
		
		$em = $this->getDoctrine()->getManager();

        $repositorio = $em->getRepository('bestnidBundle:CategoriaSubasta');
		
		$qb = $repositorio->createQueryBuilder('c');
		
		return $qb->select('c')->where('c.idPadreCategoriaSubasta IS NULL')->orderBy('c.nombreURL','ASC')->getQuery()->getResult();
		
	}
	
	/**
     * @Route("/admin/{msg}", name="admin")
     * @Template()
     */
    public function adminAction($msg = 0)
    {
		
		
		
        return ['msg' => $msg];
    }
	
	/**
     * @Route("/permisoDenegado" ,name ="permisoDenegado")
     * @Template()
     */
    public function permisoDenegadoAction()
    {
		
		
		
        return [];
    }
	
	/**
     * @Route("/errorIntegridad" ,name ="errorIntegridad")
     * @Template()
     */
    public function errorIntegridadAction()
    {
		
		
		
        return [];
    }
	
	/**
     * @Route("/subastaFinalizada" ,name ="subastaFinalizada")
     * @Template()
     */
    public function subastaFinalizadaAction()
    {
		
		
		
        return [];
    }
	
	/**
     * @Route("/subastaDueno" ,name ="subastaDueno")
     * @Template()
     */
    public function subastaDuenoAction()
    {
		
		
		
        return [];
    }
}
