/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 4/19/2019
 */

package snake.src;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Dimensions {
    private LinkedList<String> dim = new LinkedList<>();
    private LinkedList<Integer> boardDim = new LinkedList<>();
    private LinkedList<LinkedList<Integer>> wallDim = new LinkedList<>();

    /**
     * Reads the dimension file add all the values to a Linkedlist
     * @param fileName name of the file
     */
    public Dimensions(String fileName){
        Path dimensionFile = Paths.get(fileName);
        try (InputStream in = Files.newInputStream(dimensionFile);
             BufferedReader reader = new BufferedReader ( new InputStreamReader(in ))) {
            String line = null;
            while (( line = reader.readLine()) != null) {
                dim.add(line);
            }
        } catch (IOException x) {
            System.out.println("Invalid dimension file");
        }
    }

    /**
     * @return a list with the wall coordinates using the generic getList method
     */
    public LinkedList<LinkedList<Integer>> getWallDim(){
        int x = dim.size();
        for(int i = 1; i<x; i++) {
            wallDim.add(getList(i,4));
        }
        return wallDim;
    }

    /**
     * @return a list with the board coordinates using the generic getList method
     */
    public  LinkedList<Integer> getBoardDim(){
        boardDim = getList(0,2);
        return  boardDim;
    }

    /**
     * @param startPoint from where in the dim list the list start getting value
     * @param count how many of elements in the dim list should be considered
     * @return list of required items
     */
    public LinkedList<Integer> getList(int startPoint, int count){
        String s = dim.get(startPoint);
        LinkedList<Integer> list = new LinkedList<>();
        String num = "";
        int x = 0;
        for(int i = 0; i<s.length(); i++){
            while (i<s.length()&& s.charAt(i)>47 && s.charAt(i)<58){
                num = num + s.charAt(i);
                i++;
            }
            if(!num.equals("")){
                list.add(Integer.valueOf(num));
            }
            num = "";
            x++;
            if(x>=count){
                break;
            }
        }
        return list;
    }
}
