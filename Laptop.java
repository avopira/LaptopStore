class Laptop {
    private Integer serialNumber;
    private String color;
    private String model;
    private String hardDiscVolume;
    private String OS;
    private Integer RAMsizeGB;


    public Laptop (String model, Integer serialNumber, String color, String OS, String hardDiscVolume, Integer RAMsizeGB){
        this.model = model;
        this.serialNumber = serialNumber;
        this.OS = OS;
        this.hardDiscVolume = hardDiscVolume;
        this.color = color;
        this.RAMsizeGB = RAMsizeGB;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getHardDiscVolume() {
        return hardDiscVolume;
    }

    public String getOS() {
        return OS;
    }

    public Integer getRAMsizeGB(){
        return RAMsizeGB;
    }

    public int getHardDiskVolumeGB() {
        String volumeStr = hardDiscVolume.toLowerCase();
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
        + "Hard disk volume: " + hardDiscVolume + System.lineSeparator() 
        + "Serial Number: " + serialNumber +System.lineSeparator()
        + "RAM volume: " + RAMsizeGB + " GB";

        return result;
    }

}
