import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String inputStr){


        if (splitInputString(inputStr).length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] arr = splitInputString(inputStr);

                List<Input> inputList = generateInputStringList(arr);

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);

                inputList = getUniqueWords(map);

                inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input word : inputList) {
                    String wordAndCount = word.getValue() + " " +word.getWordCount();
                    joiner.add(wordAndCount);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private static List<Input> getUniqueWords(Map<String, List<Input>> map) {
        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()){
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    private static List<Input> generateInputStringList(String[] arr) {
        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        return inputList;
    }

    private static String[] splitInputString(String inputStr) {
        return inputStr.split("\\s+");
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList inputArrayList = new ArrayList<>();
                inputArrayList.add(input);
                map.put(input.getValue(), inputArrayList);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
