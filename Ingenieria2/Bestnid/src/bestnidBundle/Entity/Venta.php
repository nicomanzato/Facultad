<?php
namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity
 * @ORM\Table(name="Venta")
 */

class Venta{

	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	protected $id;
	
	/**
	* @ORM\Column(type="datetime")
	*/
	protected $fecha;
	
	/**
    * @ORM\OneToOne(targetEntity="bestnidBundle\Entity\Oferta")
    * @ORM\JoinColumn(name="oferta", referencedColumnName="id")
    */
	protected $oferta;
	
	/**
    * @ORM\ManyToOne(targetEntity="bestnidBundle\Entity\Tarjeta")
    * @ORM\JoinColumn(name="tarjeta", referencedColumnName="id")
    */
	protected $Tarjeta;
	
	/**
	* @ORM\Column(type="float")
	*/
	protected $porcentajeVenta;

	

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
     * Set respuesta
     *
     * @param \bestnidBundle\Entity\Respuesta $respuesta
     * @return Pregunta
     */
    public function setRespuesta(\bestnidBundle\Entity\Respuesta $respuesta = null)
    {
        $this->respuesta = $respuesta;
    
        return $this;
    }

    /**
     * Get respuesta
     *
     * @return \bestnidBundle\Entity\Respuesta 
     */
    public function getRespuesta()
    {
        return $this->respuesta;
    }

    /**
     * Set razon
     *
     * @param string $razon
     * @return Oferta
     */
    public function setRazon($razon)
    {
        $this->razon = $razon;
    
        return $this;
    }

    /**
     * Get razon
     *
     * @return string 
     */
    public function getRazon()
    {
        return $this->razon;
    }

    /**
     * Set monto
     *
     * @param integer $monto
     * @return Oferta
     */
    public function setMonto($monto)
    {
        $this->monto = $monto;
    
        return $this;
    }

    /**
     * Get monto
     *
     * @return integer 
     */
    public function getMonto()
    {
        return $this->monto;
    }

    /**
     * Set usuario
     *
     * @param \bestnidBundle\Entity\User $usuario
     * @return Oferta
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

    /**
     * Set ganadora
     *
     * @param boolean $ganadora
     * @return Oferta
     */
    public function setGanadora($ganadora)
    {
        $this->ganadora = $ganadora;
    
        return $this;
    }

    /**
     * Get ganadora
     *
     * @return boolean 
     */
    public function getGanadora()
    {
        return $this->ganadora;
    }

    /**
     * Set oferta
     *
     * @param \bestnidBundle\Entity\Oferta $oferta
     * @return Venta
     */
    public function setOferta($oferta = null)
    {
        $this->oferta = $oferta;
    
        return $this;
    }

    /**
     * Get oferta
     *
     * @return \bestnidBundle\Entity\Oferta 
     */
    public function getOferta()
    {
        return $this->oferta;
    }

    /**
     * Set Tarjeta
     *
     * @param \bestnidBundle\Entity\Tarjeta $tarjeta
     * @return Venta
     */
    public function setTarjeta($tarjeta = null)
    {
        $this->Tarjeta = $tarjeta;
    
        return $this;
    }

    /**
     * Get Tarjeta
     *
     * @return \bestnidBundle\Entity\Tarjeta 
     */
    public function getTarjeta()
    {
        return $this->Tarjeta;
    }

    /**
     * Set porcentajeVenta
     *
     * @param integer $porcentajeVenta
     * @return Venta
     */
    public function setPorcentajeVenta($porcentajeVenta)
    {
        $this->porcentajeVenta = $porcentajeVenta;
    
        return $this;
    }

    /**
     * Get porcentajeVenta
     *
     * @return integer 
     */
    public function getPorcentajeVenta()
    {
        return $this->porcentajeVenta;
    }
}
