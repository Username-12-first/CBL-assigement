/**
 * Print the grid of Minesweeper.
 */
public class MinesweeperApp {

    public static void main(String[] args) {
        var grid = new MinesweeperGrid();
        grid.print();
        
        //Read the game configuration parameters from the config.properties file
        String configPath =  "config.properties"; //"src/main/resources/config.properties";
        try (InputStream input = new FileInputStream(configPath)) {
            // Create a new Properties object
            Properties prop = new Properties();
            // Use the Properties objects to load the properties file
            prop.load(input);
            // get the property values and print them out
            System.out.println(prop.getProperty("difficulty.level"));
            System.out.println(prop.getProperty("mine.count"));
            System.out.println(prop.getProperty("timeout.seconds"));
            System.out.println(prop.getProperty("minegrid.dim.rows"));
            System.out.println(prop.getProperty("minegrid.dim.cols"));
            System.out.println(prop.getProperty("window.size.width.pixels"));
            System.out.println(prop.getProperty("window.size.height.pixels"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
