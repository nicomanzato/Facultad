<?php
namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity
 * @ORM\Table(name="Configuracion")
 */

class Configuracion{

	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	protected $id;
	 
	/**
	* @ORM\Column(type="string", length=70)
	* @Assert\Length(
     *      min = 5,
     *      max = 30,
     *      minMessage = "El nombre debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "El nombre no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $nombre;
	/**
	* @ORM\Column(type="string", length=10)
	* @Assert\Length(
     *      min = 0,
     *      max = 300,
     *      minMessage = "El valor debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "El valor no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $valor;
	

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
     * @return configuracion
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
     * Set valor
     *
     * @param string $valor
     * @return configuracion
     */
    public function setValor($valor)
    {
        $this->valor = $valor;

        return $this;
    }

    /**
     * Get valor
     *
     * @return string 
     */
    public function getValor()
    {
        return $this->valor;
    }
}
