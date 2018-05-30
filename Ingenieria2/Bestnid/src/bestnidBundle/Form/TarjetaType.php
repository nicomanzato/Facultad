<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class TarjetaType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('numero',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => '')))
            ->add('codigo','integer',array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => '')))
            ->add('nombre',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => '')))
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'bestnidBundle\Entity\Tarjeta'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'bestnidbundle_tarjeta';
    }
}
