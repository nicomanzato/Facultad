<?php

namespace DSSD\BonitaBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Exposicion
 *
 * @ORM\Table(name="exposicion")
 * @ORM\Entity(repositoryClass="DSSD\BonitaBundle\Repository\ExposicionRepository")
 */
class Exposicion
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
     * @var \DateTime
     *
     * @ORM\Column(name="fecha", type="datetime")
     */
    private $fecha;

    /**
     * @var string
     *
     * @ORM\Column(name="lugar", type="string", length=255)
     */
    private $lugar;

    /**
     *
     * @ORM\ManyToOne(targetEntity="Trabajo")
     * @ORM\JoinColumn(name="trabajo", referencedColumnName="id")
     */
    private $trabajo;


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
     * Set fecha
     *
     * @param \DateTime $fecha
     *
     * @return Exposicion
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
     * Set lugar
     *
     * @param string $lugar
     *
     * @return Exposicion
     */
    public function setLugar($lugar)
    {
        $this->lugar = $lugar;

        return $this;
    }

    /**
     * Get lugar
     *
     * @return string
     */
    public function getLugar()
    {
        return $this->lugar;
    }
}

