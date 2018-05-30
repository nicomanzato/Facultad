<?php
namespace bestnidBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity
 * @ORM\Table(name="Subasta")
 */

class Subasta{

	/**
	* @ORM\Column(type="integer")
	* @ORM\Id
	* @ORM\GeneratedValue(strategy="AUTO")
	*/
	protected $id;
	 /**
    * @ORM\ManyToOne(targetEntity="bestnidBundle\Entity\User")
    * @ORM\JoinColumn(name="idUserSubastador", referencedColumnName="id")
    */
	protected $idUserSubastador;
	  /**
    * @ORM\ManyToOne(targetEntity="bestnidBundle\Entity\CategoriaSubasta")
    * @ORM\JoinColumn(name="idCategoriaSubasta", referencedColumnName="id")
    */
	protected $idCategoriaSubasta;
	/**
	* @ORM\Column(type="string", length=70)
	* @Assert\Length(
     *      min = 5,
     *      max = 30,
     *      minMessage = "El titulo debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "El titulo no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $titulo;
	/**
	* @ORM\Column(type="string", length=500)
	* @Assert\Length(
     *      min = 5,
     *      max = 300,
     *      minMessage = "La descripcion de la subasta debe tener al menos {{ limit }} caracteres",
     *      maxMessage = "La descripcion de la subasta no puede tener mas de {{ limit }} caracteres"
     * )
	*/
	protected $descripcion;
	/**
	* @ORM\Column(type="integer")
	*/
	protected $estado;
	
	/**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
	public $path;
	
	/**
     * @Assert\Image(maxSize="6000000")
     */
    private $file;
	/**
	* @ORM\Column(type="datetime")
	*/
	protected $fechaPublicacion;
	
	/**
	* @ORM\Column(type="datetime")
	*/
	protected $fechaVencimiento;
	
	/**
	* @ORM\Column(type="datetime")
	*/
	protected $fechaOfertaGanadora;
	
	/**
     * @ORM\OneToMany(targetEntity="Pregunta", mappedBy="id")
	 * @ORM\JoinColumn(name="preguntas", referencedColumnName="id")
     */
    protected $preguntas;
	
	/**
     * @ORM\OneToMany(targetEntity="Oferta", mappedBy="id")
	 * @ORM\JoinColumn(name="ofertas", referencedColumnName="id")
     */
    protected $ofertas;	

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
     * Set idUserSubastador
     *
     * @param integer $idUserSubastador
     * @return Subasta
     */
    public function setIdUserSubastador($idUserSubastador)
    {
        $this->idUserSubastador = $idUserSubastador;

        return $this;
    }

    /**
     * Get idUserSubastador
     *
     * @return integer 
     */
    public function getIdUserSubastador()
    {
        return $this->idUserSubastador;
    }

    /**
     * Set idCategoriaSubasta
     *
     * @param integer $idCategoriaSubasta
     * @return Subasta
     */
    public function setIdCategoriaSubasta($idCategoriaSubasta)
    {
        $this->idCategoriaSubasta = $idCategoriaSubasta;

        return $this;
    }

    /**
     * Get idCategoriaSubasta
     *
     * @return integer 
     */
    public function getIdCategoriaSubasta()
    {
        return $this->idCategoriaSubasta;
    }

    /**
     * Set titulo
     *
     * @param string $titulo
     * @return Subasta
     */
    public function setTitulo($titulo)
    {
        $this->titulo = $titulo;

        return $this;
    }

    /**
     * Get titulo
     *
     * @return string 
     */
    public function getTitulo()
    {
        return $this->titulo;
    }

    /**
     * Set descripcion
     *
     * @param string $descripcion
     * @return Subasta
     */
    public function setDescripcion($descripcion)
    {
        $this->descripcion = $descripcion;

        return $this;
    }

    /**
     * Get descripcion
     *
     * @return string 
     */
    public function getDescripcion()
    {
        return $this->descripcion;
    }

    /**
     * Set estado
     *
     * @param boolean $estado
     * @return Subasta
     */
    public function setEstado($estado)
    {
        $this->estado = $estado;

        return $this;
    }

    /**
     * Get estado
     *
     * @return boolean 
     */
    public function getEstado()
    {
        return $this->estado;
    }
	
	public function getAbsolutePath()
    {
        return null === $this->path
            ? null
            : $this->getUploadRootDir().'/'.$this->path;
    }

    public function getWebPath()
    {
        return null === $this->path
            ? null
            : $this->getUploadDir().'/'.$this->path;
    }

    protected function getUploadRootDir()
    {
        // the absolute directory path where uploaded
        // documents should be saved
        return __DIR__.'/../../../web/'.$this->getUploadDir();
    }

    protected function getUploadDir()
    {
        // get rid of the __DIR__ so it doesn't screw up
        // when displaying uploaded doc/image in the view.
        return 'uploads/documents';
    }
	
	/**
     * Sets file.
     *
     * @param UploadedFile $file
     */
    public function setFile($file = null)
    {
        $this->file = $file;
    }

    /**
     * Get file.
     *
     * @return UploadedFile
     */
    public function getFile()
    {
        return $this->file;
    }
	
	public function upload()
	{
		// the file property can be empty if the field is not required
		if (null === $this->getFile()) {
			return;
		}

		// use the original file name here but you should
		// sanitize it at least to avoid any security issues

		// move takes the target directory and then the
		// target filename to move to
		$this->getFile()->move(
			$this->getUploadRootDir(),
			$this->getFile()->getClientOriginalName()
		);

		// set the path property to the filename where you've saved the file
		$this->path = $this->getFile()->getClientOriginalName();

		// clean up the file property as you won't need it anymore
		$this->file = null;
	}
	
	public function setFechaPublicacion($fecha){
		
		$this->fechaPublicacion = $fecha;
		
	}
	
	public function getFechaPublicacion(){
		
		return $this->fechaPublicacion;	
	}

    /**
     * Set path
     *
     * @param string $path
     * @return Subasta
     */
    public function setPath($path)
    {
        $this->path = $path;

        return $this;
    }

    /**
     * Get path
     *
     * @return string 
     */
    public function getPath()
    {
        return $this->path;
    }
	
	public function __toString(){
		
		return (string)$this->id;
		
	}
    /**
     * Constructor
     */
    public function __construct()
    {
        $this->preguntas = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Set fechaVencimiento
     *
     * @param \DateTime $fechaVencimiento
     * @return Subasta
     */
    public function setFechaVencimiento($fechaVencimiento)
    {
        $this->fechaVencimiento = $fechaVencimiento;
    
        return $this;
    }

    /**
     * Get fechaVencimiento
     *
     * @return \DateTime 
     */
    public function getFechaVencimiento()
    {
        return $this->fechaVencimiento;
    }

    /**
     * Add preguntas
     *
     * @param \bestnidBundle\Entity\Pregunta $preguntas
     * @return Subasta
     */
    public function addPregunta(\bestnidBundle\Entity\Pregunta $preguntas)
    {
        $this->preguntas[] = $preguntas;
    
        return $this;
    }

    /**
     * Remove preguntas
     *
     * @param \bestnidBundle\Entity\Pregunta $preguntas
     */
    public function removePregunta(\bestnidBundle\Entity\Pregunta $preguntas)
    {
        $this->preguntas->removeElement($preguntas);
    }

    /**
     * Get preguntas
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getPreguntas()
    {
        return $this->preguntas;
    }
	
	public function estaFinalizada(){
		
		return ( ($this->getFechaVencimiento() < new \DateTime ) or ($this->getEstado() > 0)) ;
		
	}
	
	public function puedoElegirGanadora(){
		
		return ( ($this->getFechaOfertaGanadora() < (new \DateTime('-3 day'))) and ($this->getEstado() == 1)) ;
		
	}

    /**
     * Add ofertas
     *
     * @param \bestnidBundle\Entity\Oferta $ofertas
     * @return Subasta
     */
    public function addOferta(\bestnidBundle\Entity\Oferta $ofertas)
    {
        $this->ofertas[] = $ofertas;
    
        return $this;
    }

    /**
     * Remove ofertas
     *
     * @param \bestnidBundle\Entity\Oferta $ofertas
     */
    public function removeOferta(\bestnidBundle\Entity\Oferta $ofertas)
    {
        $this->ofertas->removeElement($ofertas);
    }

    /**
     * Get ofertas
     *
     * @return \Doctrine\Common\Collections\Collection 
     */
    public function getOfertas()
    {
        return $this->ofertas;
    }

    /**
     * Set ofertaGanadora
     *
     * @param \bestnidBundle\Entity\Oferta $ofertaGanadora
     * @return Subasta
     */
    public function setOfertaGanadora(\bestnidBundle\Entity\Oferta $ofertaGanadora = null)
    {
        $this->ofertaGanadora = $ofertaGanadora;
    
        return $this;
    }

    /**
     * Get ofertaGanadora
     *
     * @return \bestnidBundle\Entity\Oferta 
     */
    public function getOfertaGanadora()
    {
        return $this->ofertaGanadora;
    }

    /**
     * Set fechaOfertaGanadora
     *
     * @param \DateTime $fechaOfertaGanadora
     * @return Subasta
     */
    public function setFechaOfertaGanadora($fechaOfertaGanadora)
    {
        $this->fechaOfertaGanadora = $fechaOfertaGanadora;
    
        return $this;
    }

    /**
     * Get fechaOfertaGanadora
     *
     * @return \DateTime 
     */
    public function getFechaOfertaGanadora()
    {
        return $this->fechaOfertaGanadora;
    }
}
