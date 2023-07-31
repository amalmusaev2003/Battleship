public enum Cell {
    EMPTYCELL("⬜"), SHIPCELL("\uD83D\uDEE5"), HALOCELL("\uD83D\uDFE6"), PADDEDCELL("\uD83D\uDFE5");
    private String img;
    Cell(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
