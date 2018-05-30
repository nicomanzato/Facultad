<?php
namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use FOS\UserBundle\Model\User as BaseUser;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity
 * @ORM\Table(name="User")
 */

class User extends BaseUser{

	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	protected $id;
	/**
	* @ORM\Column(type="string", length=20)
	 * @Assert\Length(
     *      min = 2,
     *      max = 20,
     *      minMessage = "Su nombre debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "Su nombre no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $nombre;
	/**
	* @ORM\Column(type="string", length=20)
	 * @Assert\Length(
     *      min = 2,
     *      max = 20,
     *      minMessage = "Su apellido debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "Su apellido no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $apellido;
	/**
	* @Assert\Length(
     *      min = 7,
     *      max = 15,
     *      minMessage = "Su telefono debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "Su telefono no puede tener mas de {{ limit }} caracteres"
     * )
	* @ORM\Column(type="string", length=20)
	*/
	protected $telefono;
	/**
	* @Assert\Length(
     *      min = 5,
     *      max = 40,
     *      minMessage = "Su direccion debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "Su direccion no puede tener mas de {{ limit }} caracteres"
     * )
	* @ORM\Column(type="string", length=40)
	*/
	protected $direccion;
	
	/**
     * @ORM\OneToMany(targetEntity="Subasta", mappedBy="idCategoriaSubasta")
     */
    protected $misSubastas;


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
     * Set nombre
     *
     * @param string $nombre
     * @return User
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
     * Set apellido
     *
     * @param string $apellido
     * @return User
     */
    public function setApellido($apellido)
    {
        $this->apellido = $apellido;

        return $this;
    }

    /**
     * Get apellido
     *
     * @return string 
     */
    public function getApellido()
    {
        return $this->apellido;
    }

    /**
     * Set telefono
     *
     * @param string $telefono
     * @return User
     */
    public function setTelefono($telefono)
    {
        $this->telefono = $telefono;

        return $this;
    }

    /**
     * Get telefono
     *
     * @return string 
     */
    public function getTelefono()
    {
        return $this->telefono;
    }

    /**
     * Set direccion
     *
     * @param string $direccion
     * @return User
     */
    public function setDireccion($direccion)
    {
        $this->direccion = $direccion;

        return $this;
    }

    /**
     * Get direccion
     *
     * @return string 
     */
    public function getDireccion()
    {
        return $this->direccion;
    }
    /**
     * Constructor
     */
    public function __construct()
    {
        $this->misSubastas = new \Doctrine\Common\Collections\ArrayCollection();
		parent::__construct();
    }

    /**
     * Add misSubastas
     *
     * @param \bestnidBundle\Entity\Subasta $misSubastas
     * @return User
     */
    public function addMisSubasta(\bestnidBundle\Entity\Subasta $misSubastas)
    {
        $this->misSubastas[] = $misSubastas;

        return $this;
    }

    /**
     * Remove misSubastas
     *
     * @param \bestnidBundle\Entity\Subasta $misSubastas
     */
    public function removeMisSubasta(\bestnidBundle\Entity\Subasta $misSubastas)
    {
        $this->misSubastas->removeElement($misSubastas);
    }

    /**
     * Get misSubastas
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getMisSubastas()
    {
        return $this->misSubastas;
    }
}
