<?php

namespace AppBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;

class DefaultController extends Controller
{
	/**
	* @Route("/", name="homepage")
	*/
	public function indexAction(){

		return new Response('Welcome to Symfony!');

	}

	/**
	* @Route("/nombre/{name}", name="hello")
	*/
	public function helloAction($name){
        
		return new Response($name);
    	}
}
