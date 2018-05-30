<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class CategoriaSubastaType extends AbstractType
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
            ->add('descripcionCategoriaSubasta',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => 'Categoria:')))
			->add('idPadreCategoriaSubasta',null, array( 'label' => false, 'placeholder' => 'Elija una categoria', 'attr' => array('class' => 'form-control form-group'), 'choices' => $this->categorias))
        	->add('nombreURL',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => 'Nombre URL:')))
			;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'bestnidBundle\Entity\CategoriaSubasta'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'bestnidbundle_categoriasubasta';
    }
}
