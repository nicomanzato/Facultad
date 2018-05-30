<?php
namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity
 * @ORM\Table(name="Respuesta")
 */

class Respuesta{

	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	protected $id;
	
	/**
	* @ORM\Column(type="datetime", length=500)
	*/
	protected $fecha;
	
	/**
     * @ORM\Column(type="string", length=255)
	 * @Assert\Length(
     *      min = 1,
     *      max = 255,
     *      minMessage = "La pregunta debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "La pregunta no puede tener mas de {{ limit }} caracteres"
     * )
     */
	public $contenido;
	
	/**
    * @ORM\OneToOne(targetEntity="bestnidBundle\Entity\Pregunta")
    * @ORM\JoinColumn(name="Pregunta", referencedColumnName="id")
    */
	protected $pregunta;

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
     * Set fecha
     *
     * @param \DateTime $fecha
     * @return Pregunta
     */
    public function setFecha($fecha)
    {
        $this->fecha = $fecha;
    
        return $this;
    }

    /**
     * Get fecha
     *
     * @return \DateTime 
     */
    public function getFecha()
    {
        return $this->fecha;
    }

    /**
     * Set contenido
     *
     * @param string $contenido
     * @return Pregunta
     */
    public function setContenido($contenido)
    {
        $this->contenido = $contenido;
    
        return $this;
    }

    /**
     * Get contenido
     *
     * @return string 
     */
    public function getContenido()
    {
        return $this->contenido;
    }

    /**
     * Set subasta
     *
     * @param \bestnidBundle\Entity\Subasta $subasta
     * @return Pregunta
     */
    public function setSubasta( $subasta = null)
    {
        $this->subasta = $subasta;
    
        return $this;
    }

    /**
     * Get subasta
     *
     * @return \bestnidBundle\Entity\Subasta 
     */
    public function getSubasta()
    {
        return $this->subasta;
    }

    /**
     * Set pregunta
     *
     * @param \bestnidBundle\Entity\Pregunta $pregunta
     * @return Respuesta
     */
    public function setPregunta($pregunta = null)
    {
        $this->pregunta = $pregunta;
    
        return $this;
    }

    /**
     * Get pregunta
     *
     * @return \bestnidBundle\Entity\Pregunta 
     */
    public function getPregunta()
    {
        return $this->pregunta;
    }
}