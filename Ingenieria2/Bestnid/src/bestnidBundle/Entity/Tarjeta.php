<?php

namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity
 * @ORM\Table(name="Tarjeta")
 */
 
 class Tarjeta{
 
	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	
	protected $id;
	
	/**
	 *@ORM\Column(type="string")
     * @Assert\CardScheme(
     *     schemes={"VISA", "MASTERCARD"},
     *     message="Tu numero de tarjeta es invalido."
     * )
	*/
	protected $numero;
	/**
	* @ORM\Column(type="bigint", length=3)
	* @Assert\Length(
     *      min = 3,
     *      max = 3,
     *      minMessage = "El codigo debe tener {{ limit }} caracteres",
     *      maxMessage = "El codigo debe tener {{ limit }} caracteres"
     * )
	*/
	protected $codigo;
	/**
	* @ORM\Column(type="string", length=500)
	* @Assert\Length(
     *      min = 3,
     *      max = 40,
     *      minMessage = "El nombre de la persona a cargo de la tarjeta debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "El nombre de la persona a cargo de la tarjeta no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $nombre;
	
	/**
    * @ORM\ManyToOne(targetEntity="bestnidBundle\Entity\User")
    * @ORM\JoinColumn(name="usuario", referencedColumnName="id")
    */
	protected $usuario;
	
	
	
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
	

    /**
     * Set numero
     *
     * @param string $numero
     * @return Tarjeta
     */
    public function setNumero($numero)
    {
        $this->numero = $numero;
    
        return $this;
    }

    /**
     * Get numero
     *
     * @return string 
     */
    public function getNumero()
    {
        return $this->numero;
    }

    /**
     * Set codigo
     *
     * @param string $codigo
     * @return Tarjeta
     */
    public function setCodigo($codigo)
    {
        $this->codigo = $codigo;
    
        return $this;
    }

    /**
     * Get codigo
     *
     * @return string 
     */
    public function getCodigo()
    {
        return $this->codigo;
    }

    /**
     * Set nombre
     *
     * @param string $nombre
     * @return Tarjeta
     */
    public function setNombre($nombre)
    {
        $this->nombre = $nombre;
    
        return $this;
    }

    /**
     * Get nombre
     *
     * @return string 
     */
    public function getNombre()
    {
        return $this->nombre;
    }

    /**
     * Set usuario
     *
     * @param \bestnidBundle\Entity\User $usuario
     * @return Tarjeta
     */
    public function setUsuario(\bestnidBundle\Entity\User $usuario = null)
    {
        $this->usuario = $usuario;
    
        return $this;
    }

    /**
     * Get usuario
     *
     * @return \bestnidBundle\Entity\User 
     */
    public function getUsuario()
    {
        return $this->usuario;
    }
	
	public function __toString(){
		
		return $this->getNumero();
		
	}
}
