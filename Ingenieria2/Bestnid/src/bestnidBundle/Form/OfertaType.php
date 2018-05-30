<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class OfertaType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('razon','textarea',array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('rows' => 5,'class' => 'form-control form-group', 'placeholder' => '')))
            ->add('monto',null,array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('class' => 'form-control form-group', 'placeholder' => '')))
            ->add('subasta','hidden')
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'bestnidBundle\Entity\Oferta'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'bestnidbundle_oferta';
    }
}
