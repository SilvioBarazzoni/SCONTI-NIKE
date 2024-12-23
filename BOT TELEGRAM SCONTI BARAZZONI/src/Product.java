class Product {
    public String link;
    public String prezzo;
    public String prezzos;

    public Product(String link, String prezzo, String prezzos) {
        this.link = link;
        this.prezzo = prezzo;
        this.prezzos = prezzos;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", link='" + link + '\'' +
                ", prezzo='" + prezzo + '\'' +
                ", prezzos='" + prezzos + '\'' +
                '}';
    }
}
