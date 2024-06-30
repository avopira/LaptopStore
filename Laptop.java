class Laptop {
    private String color;
    private String model;
    private String hardDiskVolume;
    private String OS;
    private Integer RAMsizeGB;


    public Laptop (String model, String color, String OS, String hardDiskVolume, Integer RAMsizeGB){
        this.model = model;
        this.OS = OS;
        this.hardDiskVolume = hardDiskVolume;
        this.color = color;
        this.RAMsizeGB = RAMsizeGB;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getHardDiskVolume() {
        return hardDiskVolume;
    }

    public String getOS() {
        return OS;
    }

    public Integer getRAMsizeGB(){
        return RAMsizeGB;
    }

    public int getHardDiskVolumeGB() {
        String volumeStr = hardDiskVolume.toLowerCase();
        if (volumeStr.contains("tb")) {
            return Integer.parseInt(volumeStr.replace(" tb", "")) * 1024;
        } else if (volumeStr.contains("gb")) {
            return Integer.parseInt(volumeStr.replace(" gb", ""));
        }
        return 0;
    }

    public String toString(){
        String result = "Laptop model: " + model + System.lineSeparator()
        + "Color: " + color + System.lineSeparator() 
        + "Operating system: " + OS + System.lineSeparator()
        + "Hard disk volume: " + hardDiskVolume + System.lineSeparator() 
        + "RAM volume: " + RAMsizeGB + " GB";

        return result;
    }

}
