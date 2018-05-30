<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class SubastaType extends AbstractType
{
	private $categorias;
	
	public function __construct($routeSetup)
	{
		$this->categorias = $routeSetup;
	}
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('idCategoriaSubasta',null, array( 'required' => true,'label' => false, 'placeholder' => 'Elija una categoria para la subasta', 'attr' => array('class' => 'form-control form-group'), 'choices' => $this->categorias ))
            ->add('titulo',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => 'Ingrese el titulo de la subasta')))
            ->add('descripcion','textarea',array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('rows' => 5,'class' => 'form-control form-group', 'placeholder' => 'Ingrese una descipcion')))
            ->add('file','file',array('label' => false , 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => 'Link a Imagen')))
			->add('fechaVencimiento','choice', array( 'choices' => array('5' => '5 dias', '7' => '7 dias') ))
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'bestnidBundle\Entity\Subasta'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'bestnidbundle_subasta';
    }
}
