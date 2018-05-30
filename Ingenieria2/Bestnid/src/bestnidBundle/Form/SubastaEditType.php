<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class SubastaEditType extends AbstractType
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
            ->add('idCategoriaSubasta',null, array( 'required' => true,'label' => false, 'placeholder' => 'Elija una categoria', 'attr' => array('class' => 'form-control form-group'), 'choices' => $this->categorias ))
            ->add('titulo',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => 'Titulo:')))
            ->add('descripcion','textarea',array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('rows' => 5,'class' => 'form-control form-group', 'placeholder' => 'Descipcion')))
            ->add('file','file',array('required' => false,'label' => false , 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => 'Link a Imagen')))
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
