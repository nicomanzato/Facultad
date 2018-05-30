<?php

namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity
 * @ORM\Table(name="Contacto")
 */
 
 class Contacto{
 
	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	
	protected $id;
	/**
	* @ORM\Column(type="string", length=20)
	* @Assert\Length(
     *      min = 3,
     *      max = 30,
     *      minMessage = "El asunto debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "El asunto no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $asunto;
	/**
	* @ORM\Column(type="string", length=100)
	* @Assert\Length(
     *      min = 3,
     *      max = 30,
     *      minMessage = "Debe detallar al menos {{ limit }} caracteres",
     *      maxMessage = "No puede detallar mas de {{ limit }} caracteres"
     * )
	*/
	protected $comentario;
	/**
	* @ORM\Column(type="string", length=500)
	* @Assert\Length(
     *      min = 3,
     *      max = 30,
     *      minMessage = "Su e-mail debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "Su e-mail no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $mail;
	/**
	* @ORM\Column(type="string", length=30)
	*/
	
	/**
     * Get id
     *
     * @return integer 
     */
    public function getId()
    {
        return $this->id;
    }
	
	/**
     * Get asunto
     *
     * @return string 
     */
	public function getAsunto()
    {
       return $this->asunto;
    }
	
	 /**
     * Set asunto
     *
     * @param string $asunto
     * @return Contacto
     */
	public function setAsunto($asunto)
    {
        $this->asunto = $asunto;

        return $this;
    }
	
	/**
     * Get comentario
     *
     * @return string 
     */
	public function getComentario()
    {
       return $this->comentario;
    }
	 /**
     * Set comentario
     *
     * @param string $comentario
     * @return Contacto
     */
	public function setComentario($comentario)
    {
        $this->comentario = $comentario;

        return $this;
    }
	
	/**
     * Get mail
     *
     * @return string 
     */
	public function getMail()
    {
       return $this->mail;
    }
	
	/**
     * Set mail
     *
     * @param string $mail
     * @return Contacto
     */
	public function setMail($mail)
    {
        $this->mail = $mail;

        return $this;
    }
	
}

