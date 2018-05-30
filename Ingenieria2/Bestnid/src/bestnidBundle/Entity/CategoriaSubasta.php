<?php
namespace bestnidBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity
 * @ORM\Table(name="CategoriaSubasta")
 */

class CategoriaSubasta{

	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	protected $id;
	/**
	* @ORM\Column(type="string", length=30, unique=true)
	* @Assert\Length(
     *      min = 3,
     *      max = 30,
     *      minMessage = "La descripcion de la categoria debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "La descripcion de la categoria no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $descripcionCategoriaSubasta;
	
	/**
    * @ORM\ManyToOne(targetEntity="bestnidBundle\Entity\CategoriaSubasta")
    * @ORM\JoinColumn(name="idPadreCategoriaSubasta", referencedColumnName="id")
    */
	protected $idPadreCategoriaSubasta;
	
	/**
     * @ORM\OneToMany(targetEntity="CategoriaSubasta", mappedBy="idPadreCategoriaSubasta")
     */
    protected $CategoriasHijas;
		
	/**
	* @ORM\Column(type="string", length=40)
	* @ORM\Column(type="string", length=30)
	* @Assert\Length(
     *      min = 3,
     *      max = 40,
     *      minMessage = "El nombre de la URL debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "El nombre de la URL no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $nombreURL;
	/**
     * @ORM\OneToMany(targetEntity="Subasta", mappedBy="idCategoriaSubasta")
     */
    protected $Subastas;

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
     * Set descripcionCategoriaSubasta
     *
     * @param string $descripcionCategoriaSubasta
     * @return CategoriaSubasta
     */
    public function setDescripcionCategoriaSubasta($descripcionCategoriaSubasta)
    {
        $this->descripcionCategoriaSubasta = $descripcionCategoriaSubasta;

        return $this;
    }

    /**
     * Get descripcionCategoriaSubasta
     *
     * @return string 
     */
    public function getDescripcionCategoriaSubasta()
    {
        return $this->descripcionCategoriaSubasta;
    }

    /**
     * Set idPadreCategoriaSubasta
     *
     * @param integer $idPadreCategoriaSubasta
     * @return CategoriaSubasta
     */
    public function setIdPadreCategoriaSubasta($idPadreCategoriaSubasta)
    {
        $this->idPadreCategoriaSubasta = $idPadreCategoriaSubasta;

        return $this;
    }

    /**
     * Get idPadreCategoriaSubasta
     *
     * @return integer 
     */
    public function getIdPadreCategoriaSubasta()
    {
        return $this->idPadreCategoriaSubasta;
    }


    /**
     * Set nombreURL
     *
     * @param string $nombreURL
     * @return CategoriaSubasta
     */
    public function setNombreURL($nombreURL)
    {
        $this->nombreURL = $nombreURL;

        return $this;
    }

    /**
     * Get nombreURL
     *
     * @return string 
     */
    public function getNombreURL()
    {
        return $this->nombreURL;
    }
	
	public function __toString()
    {
        return $this->getDescripcionCategoriaSubasta();
    }

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->Subastas = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Add Subastas
     *
     * @param \bestnidBundle\Entity\Subasta $subastas
     * @return CategoriaSubasta
     */
    public function addSubasta(\bestnidBundle\Entity\Subasta $subastas)
    {
        $this->Subastas[] = $subastas;

        return $this;
    }

    /**
     * Remove Subastas
     *
     * @param \bestnidBundle\Entity\Subasta $subastas
     */
    public function removeSubasta(\bestnidBundle\Entity\Subasta $subastas)
    {
        $this->Subastas->removeElement($subastas);
    }

    /**
     * Get Subastas
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getSubastas()
    {
        return $this->Subastas;
    }

    /**
     * Add CategoriasHijas
     *
     * @param \bestnidBundle\Entity\CategoriaSubasta $categoriasHijas
     * @return CategoriaSubasta
     */
    public function addCategoriasHija(\bestnidBundle\Entity\CategoriaSubasta $categoriasHijas)
    {
        $this->CategoriasHijas[] = $categoriasHijas;

        return $this;
    }

    /**
     * Remove CategoriasHijas
     *
     * @param \bestnidBundle\Entity\CategoriaSubasta $categoriasHijas
     */
    public function removeCategoriasHija(\bestnidBundle\Entity\CategoriaSubasta $categoriasHijas)
    {
        $this->CategoriasHijas->removeElement($categoriasHijas);
    }

    /**
     * Get CategoriasHijas
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getCategoriasHijas()
    {
        return $this->CategoriasHijas;
    }
}
