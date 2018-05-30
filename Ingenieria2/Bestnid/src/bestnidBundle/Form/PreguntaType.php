<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class PreguntaType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('fecha','hidden')
            ->add('subasta','hidden')
            ->add('contenido','textarea',array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('rows' => 5,'class' => 'form-control form-group', 'placeholder' => 'Ingrese su pregunta')))
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'bestnidBundle\Entity\Pregunta'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'bestnidbundle_pregunta';
    }
}
