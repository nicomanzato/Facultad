<?php

namespace DSSD\BonitaBundle\Controller;

use FOS\RestBundle\Controller\FOSRestController;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use FOS\RestBundle\Controller\Annotations\Get;
use FOS\RestBundle\Controller\Annotations\Post;
use FOS\RestBundle\Controller\Annotations\Delete;
use FOS\RestBundle\Controller\Annotations\Put;
use FOS\RestBundle\Controller\Annotations\View;

class RestController extends FOSRestController
{


    /**
    * Get all the tasks
     * @return array
     *
     * @View()
     * @Get("/")
     */
    public function getExposicionAction()
    {
        $em = $this->getDoctrine()->getManager();

        $data = $em->getRepository('DSSDBonitaBundle:Exposicion')->findAll();

        $view = $this->view($data, 200)
            ->setTemplate("DSSDBonitaBundle:Exposicion:index.html.twig")
            ->setTemplateVar('users')
        ;

        return $data;
    }

}
