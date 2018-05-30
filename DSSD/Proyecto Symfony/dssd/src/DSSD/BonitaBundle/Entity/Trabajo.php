<?php

namespace DSSD\BonitaBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Trabajo
 *
 * @ORM\Table(name="trabajo")
 * @ORM\Entity(repositoryClass="DSSD\BonitaBundle\Repository\TrabajoRepository")
 */
class Trabajo
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="titulo", type="string", length=255)
     */
    private $titulo;

    /**
     * @var string
     *
     * @ORM\Column(name="resumen", type="string", length=255)
     */
    private $resumen;

    /**
     * @var string
     *
     * @ORM\Column(name="tema", type="string", length=255)
     */
    private $tema;

    /**
     * @var string
     *
     * @ORM\Column(name="tipoPresentacion", type="string", length=255)
     */
    private $tipoPresentacion;

    /**
     *
     * @ORM\ManyToOne(targetEntity="Autor")
     * @ORM\JoinColumn(name="autor", referencedColumnName="id")
     */
    private $autor;

    /**
     *
     * @ORM\ManyToMany(targetEntity="Autor")
     * @ORM\JoinColumn(name="autor", referencedColumnName="id")
     */
    private $autoresSecundarios;

    /**
     * Significa que un revisor aprobo el trabajo
     *
     * @var boolean
     *
     * @ORM\Column(name="aprobado", type="boolean")
     */
    private $aprobado;

    /**
     * Significa que el trabajo ya fue asignado a un revisor en Bonita
     *
     * @var string
     *
     * @ORM\Column(name="asignado", type="boolean")
     */
    private $asignado;

    /**
     * Significa que el autor ya dio por finalizado el archivo de Google Drive
     *
     * @var string
     *
     * @ORM\Column(name="finalizado", type="boolean")
     */
    private $finalizado;

    /**
     * @var string
     *
     * @ORM\Column(name="idGoogleDriveFile", type="string")
     */
    private $idGoogleDriveFile;

     /**
     * @var string
     *
     * @ORM\Column(name="exposicionDia", type="datetime")
     */
    private $exposicionDia; 

    /**
     * @var string
     *
     * @ORM\Column(name="exposicionLugar", type="string", length=255)
     */
    private $exposicionLugar;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set titulo
     *
     * @param string $titulo
     *
     * @return Trabajo
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
     * Set resumen
     *
     * @param string $resumen
     *
     * @return Trabajo
     */
    public function setResumen($resumen)
    {
        $this->resumen = $resumen;

        return $this;
    }

    /**
     * Get resumen
     *
     * @return string
     */
    public function getResumen()
    {
        return $this->resumen;
    }

    /**
     * Set tema
     *
     * @param string $tema
     *
     * @return Trabajo
     */
    public function setTema($tema)
    {
        $this->tema = $tema;

        return $this;
    }

    /**
     * Get tema
     *
     * @return string
     */
    public function getTema()
    {
        return $this->tema;
    }

    /**
     * Set tipoPresentacion
     *
     * @param string $tipoPresentacion
     *
     * @return Trabajo
     */
    public function setTipoPresentacion($tipoPresentacion)
    {
        $this->tipoPresentacion = $tipoPresentacion;

        return $this;
    }

    /**
     * Get tipoPresentacion
     *
     * @return string
     */
    public function getTipoPresentacion()
    {
        return $this->tipoPresentacion;
    }

    /**
     * Set autor
     *
     * @param string $autor
     *
     * @return Trabajo
     */
    public function setAutor($autor)
    {
        $this->autor = $autor;

        return $this;
    }

    /**
     * Get autor
     *
     * @return string
     */
    public function getAutor()
    {
        return $this->autor;
    }
    /**
     * Constructor
     */
    public function __construct()
    {
        $this->autoresSecundarios = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Add autoresSecundario
     *
     * @param \DSSD\BonitaBundle\Entity\Autor $autoresSecundario
     *
     * @return Trabajo
     */
    public function addAutoresSecundario(\DSSD\BonitaBundle\Entity\Autor $autoresSecundario)
    {
        $this->autoresSecundarios[] = $autoresSecundario;

        return $this;
    }

    /**
     * Remove autoresSecundario
     *
     * @param \DSSD\BonitaBundle\Entity\Autor $autoresSecundario
     */
    public function removeAutoresSecundario(\DSSD\BonitaBundle\Entity\Autor $autoresSecundario)
    {
        $this->autoresSecundarios->removeElement($autoresSecundario);
    }

    /**
     * Get autoresSecundarios
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getAutoresSecundarios()
    {
        return $this->autoresSecundarios;
    }

    /**
     * Set aprobado
     *
     * @param boolean $aprobado
     *
     * @return Trabajo
     */
    public function setAprobado($aprobado)
    {
        $this->aprobado = $aprobado;

        return $this;
    }

    /**
     * Get aprobado
     *
     * @return boolean
     */
    public function getAprobado()
    {
        return $this->aprobado;
    }

    /**
     * Set asignado
     *
     * @param boolean $asignado
     *
     * @return Trabajo
     */
    public function setAsignado($asignado)
    {
        $this->asignado = $asignado;

        return $this;
    }

    /**
     * Get asignado
     *
     * @return boolean
     */
    public function getAsignado()
    {
        return $this->asignado;
    }

    /**
     * Set finalizado
     *
     * @param boolean $finalizado
     *
     * @return Trabajo
     */
    public function setFinalizado($finalizado)
    {
        $this->finalizado = $finalizado;

        return $this;
    }

    /**
     * Get finalizado
     *
     * @return boolean
     */
    public function getFinalizado()
    {
        return $this->finalizado;
    }

    /**
     * Set idGoogleDriveFile
     *
     * @param \long $idGoogleDriveFile
     *
     * @return Trabajo
     */
    public function setIdGoogleDriveFile(\long $idGoogleDriveFile)
    {
        $this->idGoogleDriveFile = $idGoogleDriveFile;

        return $this;
    }

    /**
     * Get idGoogleDriveFile
     *
     * @return \long
     */
    public function getIdGoogleDriveFile()
    {
        return $this->idGoogleDriveFile;
    }

    /**
     * Set exposicion
     *
     * @param \DSSD\BonitaBundle\Entity\Exposicion $exposicion
     *
     * @return Trabajo
     */
    public function setExposicion(\DSSD\BonitaBundle\Entity\Exposicion $exposicion = null)
    {
        $this->exposicion = $exposicion;

        return $this;
    }

    /**
     * Get exposicion
     *
     * @return \DSSD\BonitaBundle\Entity\Exposicion
     */
    public function getExposicion()
    {
        return $this->exposicion;
    }

    /**
     * Add exposicion
     *
     * @param \DSSD\BonitaBundle\Entity\Exposicion $exposicion
     *
     * @return Trabajo
     */
    public function addExposicion(\DSSD\BonitaBundle\Entity\Exposicion $exposicion)
    {
        $this->exposicion[] = $exposicion;

        return $this;
    }

    /**
     * Remove exposicion
     *
     * @param \DSSD\BonitaBundle\Entity\Exposicion $exposicion
     */
    public function removeExposicion(\DSSD\BonitaBundle\Entity\Exposicion $exposicion)
    {
        $this->exposicion->removeElement($exposicion);
    }

    /**
     * Set exposicionDia
     *
     * @param \DateTime $exposicionDia
     *
     * @return Trabajo
     */
    public function setExposicionDia($exposicionDia)
    {
        $this->exposicionDia = $exposicionDia;

        return $this;
    }

    /**
     * Get exposicionDia
     *
     * @return \DateTime
     */
    public function getExposicionDia()
    {
        return $this->exposicionDia;
    }

    /**
     * Set exposicionLugar
     *
     * @param string $exposicionLugar
     *
     * @return Trabajo
     */
    public function setExposicionLugar($exposicionLugar)
    {
        $this->exposicionLugar = $exposicionLugar;

        return $this;
    }

    /**
     * Get exposicionLugar
     *
     * @return string
     */
    public function getExposicionLugar()
    {
        return $this->exposicionLugar;
    }
}
