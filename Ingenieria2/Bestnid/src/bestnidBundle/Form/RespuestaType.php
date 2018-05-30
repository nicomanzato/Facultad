<?php

namespace bestnidBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class RespuestaType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('fecha','hidden')
            ->add('pregunta','hidden')
            ->add('contenido','textarea',array('label' => false, 'translation_domain' => 'FOSUserBundle', 'attr' => array('rows' => 3,'class' => 'form-control form-group', 'placeholder' => 'Ingrese su respuesta')))
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'bestnidBundle\Entity\Respuesta'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'bestnidbundle_respuesta';
    }
}
