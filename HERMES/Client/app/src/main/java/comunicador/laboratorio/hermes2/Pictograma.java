package comunicador.laboratorio.hermes2;

/**
 * Created by nicolasmanzato on 13/12/15.
 */
public class Pictograma {

    private Integer imagen;
    private String descripcion;
    private Integer sonido;
    private Integer categoria;

    public Pictograma(String imagen) {

        switch (imagen) {

            case "aro": {
                this.setImagen(R.drawable.aro);
                this.setSonido(R.raw.aro);
                this.setCategoria(0);
                break;
            }
            case "asustado_chica": {
                this.setImagen(R.drawable.asustado_chica);
                this.setSonido(R.raw.asustada);
                this.setCategoria(2);
                break;
            }
            case "asustado_chico": {
                this.setImagen(R.drawable.asustado_chico);
                this.setSonido(R.raw.asustado);
                this.setCategoria(2);
                break;
            }
            case "bano": {
                this.setImagen(R.drawable.bano);
                this.setSonido(R.raw.banio);
                this.setCategoria(3);
                break;
            }
            case "broches": {
                this.setImagen(R.drawable.broches);
                this.setSonido(R.raw.broches);
                this.setCategoria(0);
                break;
            }
            case "burbujas": {
                this.setImagen(R.drawable.burbujas);
                this.setSonido(R.raw.burbujas);
                this.setCategoria(0);
                break;
            }
            case "caballo_blanco": {
                this.setImagen(R.drawable.caballo_blanco);
                this.setSonido(R.raw.caballo);
                this.setCategoria(1);
                break;
            }
            case "caballo_marron": {
                this.setImagen(R.drawable.caballo_marron);
                this.setSonido(R.raw.caballo);
                this.setCategoria(1);
                break;
            }
            case "caballo_negro": {
                this.setImagen(R.drawable.caballo_negro);
                this.setSonido(R.raw.caballo);
                this.setCategoria(1);
                break;
            }
            case "cansado_chica": {
                this.setImagen(R.drawable.cansado_chica);
                this.setCategoria(2);
                this.setSonido(R.raw.cansada);
                break;
            }
            case "casco": {
                this.setCategoria(1);
                this.setImagen(R.drawable.casco);
                this.setSonido(R.raw.casco);
                break;
            }
            case "cepillo": {
                this.setCategoria(1);
                this.setImagen(R.drawable.cepillo);
                this.setSonido(R.raw.cepillo);
                break;
            }
            case "chapas": {
                this.setCategoria(0);
                this.setImagen(R.drawable.chapas);
                this.setSonido(R.raw.chapas);
                break;
            }
            case "contento_chica": {
                this.setCategoria(2);
                this.setImagen(R.drawable.contento_chica);
                this.setSonido(R.raw.contenta);
                break;
            }
            case "contento_chico": {
                this.setCategoria(2);
                this.setImagen(R.drawable.contento_chico);
                this.setSonido(R.raw.contento);
                break;
            }
            case "cubos": {
                this.setCategoria(0);
                this.setImagen(R.drawable.cubos);
                this.setSonido(R.raw.cubos);
                break;
            }
            case "dolorido_chica": {
                this.setCategoria(2);
                this.setImagen(R.drawable.dolorido_chica);
                this.setSonido(R.raw.me_duele);
                break;
            }
            case "dolorido_chico": {
                this.setCategoria(2);
                this.setImagen(R.drawable.dolorido_chico);
                this.setSonido(R.raw.me_duele);
                break;
            }
            case "enojado_chica": {
                this.setCategoria(2);
                this.setImagen(R.drawable.enojado_chica);
                this.setSonido(R.raw.enojada);
                break;
            }
            case "enojado_chico": {
                this.setCategoria(2);
                this.setImagen(R.drawable.enojado_chico);
                this.setSonido(R.raw.enojado);
                break;
            }
            case "escarba_vasos": {
                this.setCategoria(1);
                this.setImagen(R.drawable.escarba_vasos);
                this.setSonido(R.raw.escarba_vasos);
                break;
            }
            case "letras": {
                this.setCategoria(0);
                this.setImagen(R.drawable.letras);
                this.setSonido(R.raw.letras);
                break;
            }
            case "limpieza": {
                this.setCategoria(0);
                this.setImagen(R.drawable.limpieza);
                this.setSonido(R.raw.limpieza);
                break;
            }
            case "maracas": {
                this.setCategoria(0);
                this.setImagen(R.drawable.maracas);
                this.setSonido(R.raw.maracas);
                break;
            }
            case "matra": {
                this.setCategoria(1);
                this.setImagen(R.drawable.matra);
                this.setSonido(R.raw.matra);
                break;
            }
            case "montura": {
                this.setCategoria(1);
                this.setImagen(R.drawable.montura);
                this.setSonido(R.raw.montura);
                break;
            }
            case "no": {
                this.setCategoria(2);
                this.setImagen(R.drawable.no);
                this.setSonido(R.raw.no);
                break;
            }
            case "palos": {
                this.setCategoria(1);
                this.setImagen(R.drawable.palos);
                this.setSonido(R.raw.palos);
                break;
            }
            case "pasto": {
                this.setCategoria(1);
                this.setImagen(R.drawable.pasto);
                this.setSonido(R.raw.pasto);
                break;
            }
            case "pato": {
                this.setCategoria(0);
                this.setImagen(R.drawable.pato);
                this.setSonido(R.raw.pato);
                break;
            }
            case "pelota": {
                this.setCategoria(0);
                this.setImagen(R.drawable.pelota);
                this.setSonido(R.raw.pelota);
                break;
            }
            case "raqueta_blanca": {
                this.setCategoria(1);
                this.setImagen(R.drawable.raqueta_blanca);
                this.setSonido(R.raw.rasqueta_blanda);
                break;
            }
            case "raqueta_dura": {
                this.setCategoria(1);
                this.setImagen(R.drawable.raqueta_dura);
                this.setSonido(R.raw.rasqueta_dura);
                break;
            }
            case "riendas": {
                this.setCategoria(1);
                this.setImagen(R.drawable.riendas);
                this.setSonido(R.raw.riendas);
                break;
            }
            case "sed_chica": {
                this.setCategoria(3);
                this.setImagen(R.drawable.sed_chica);
                this.setSonido(R.raw.sed);
                break;
            }
            case "sed_chico": {
                this.setCategoria(3);
                this.setImagen(R.drawable.sed_chico);
                this.setSonido(R.raw.sed);
                break;
            }
            case "si": {
                this.setCategoria(2);
                this.setImagen(R.drawable.si);
                this.setSonido(R.raw.si);
                break;
            }
            case "sorprendido_chica": {
                this.setCategoria(2);
                this.setImagen(R.drawable.sorprendido_chica);
                this.setSonido(R.raw.sorprendida);
                break;
            }
            case "sorprendido_chico": {
                this.setCategoria(2);
                this.setImagen(R.drawable.sorprendido_chico);
                this.setSonido(R.raw.sorprendida);
                break;
            }
            case "tarima": {
                this.setCategoria(0);
                this.setImagen(R.drawable.tarima);
                this.setSonido(R.raw.tarima);
                break;
            }
            case "triste_chica": {
                this.setCategoria(2);
                this.setImagen(R.drawable.triste_chica);
                this.setSonido(R.raw.triste);
                break;
            }
            case "triste_chico": {
                this.setCategoria(2);
                this.setImagen(R.drawable.triste_chico);
                this.setSonido(R.raw.triste);
                break;
            }
            case "zanahoria": {
                this.setCategoria(1);
                this.setImagen((R.drawable.zanahoria));
                this.setSonido(R.raw.zanahoria);
                break;
            }

        }

        this.setDescripcion(imagen);

    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSonido() {
        return sonido;
    }

    public static String getName(String desc){
        switch (desc) {
            case "aro": {
                return "Aro";
            }
            case "asustado_chica": {
                return "Asustada";
            }
            case "asustado_chico": {
                return "Asustado";
            }
            case "bano": {
                return "Bano";
            }
            case "broches": {
                return "Broches";
            }
            case "burbujas": {
                return "Burbujas";
            }
            case "caballo_blanco": {
                return "Caballo Blanco";
            }
            case "caballo_marron": {
                return "Caballo Marron";
            }
            case "caballo_negro": {
                return "Caballo Negro";
            }
            case "cansado_chica": {
                return "Cansado Chica";
            }
            case "casco": {
                return "Casco";
            }
            case "cepillo": {
                return "Cepillo";
            }
            case "chapas": {
                return "Chapas";
            }
            case "contento_chica": {
                return "Contenta";
            }
            case "contento_chico": {
                return "Contento";
            }
            case "cubos": {
                return "Cubos";
            }
            case "dolorido_chica": {
                return "Dolorida";
            }
            case "dolorido_chico": {
                return "Dolorido";
            }
            case "enojado_chica": {
                return "Enojada";
            }
            case "enojado_chico": {
                return "Enojado";
            }
            case "escarba_vasos": {
                return "Escarba vasos";
            }
            case "letras": {
                return "Letras";
            }
            case "limpieza": {
                return "Limpieza";
            }
            case "maracas": {
                return "Maracas";
            }
            case "matra": {
                return "Matra";
            }
            case "montura": {
                return "Montura";
            }
            case "palos": {
                return "Palos";
            }
            case "pasto": {
                return "Pasto";
            }
            case "pato": {
                return "Pato";
            }
            case "pelota": {
                return "Pelota";
            }
            case "raqueta_blanca": {
                return "Raqueta Blanca";
            }
            case "raqueta_dura": {
                return "Raqueta Dura";
            }
            case "riendas": {
                return "Riendas";
            }
            case "sed_chica": {
                return "Sed";
            }
            case "sed_chico": {
                return "Sed";
            }
            case "sorprendido_chica": {
                return "Sorprendida";
            }
            case "sorprendido_chico": {
                return "Sorprendido";
            }
            case "tarima": {
                return "Tarima";
            }
            case "triste_chica": {
                return "Triste";
            }
            case "triste_chico": {
                return "Triste";
            }
            case "zanahoria": {
                return "Zanahoria";
            }
            default:
                return "";
        }
    }

    public void setSonido(Integer sonido) {
        this.sonido = sonido;
    }

}
