/**
 * This source code is licensed under the CC-BY-NC.
 * This source code is developed by Chromaryu.
 * @author Chromaryu
 */
package net.fumyatan.advancedwhoisplus_reloaded.Utils;



/**
 * LICENSE: CC-BY-NC
 * Web: http://chromaryu.ml
 * @author Chromaryu
 */
public class UUIDdasher {
    /**
     * dash is make 8-4-4-4-12 pattern UUID-like string to be dashed.
     * @param uuid 8-4-4-4-12 UUID-like String.
     * @throws IllegalArgumentException if uuid isn't 8-4-4-4-12 pattern.
     * @return Returns dashed UUID String
     */
    public static String dash(String uuid) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if(uuid.length() == 32) {
            //StringBuilder sb = new StringBuilder();
            String[] uuid_str = new String[5];// They should be 8-4-4-4-12 pattern.....
            int i = 0;
            uuid_str[0] = uuid.substring(0, 8); // 8
            uuid_str[1] = uuid.substring(8, 12); // 4-1
            uuid_str[2] = uuid.substring(12, 16); // 4-2
            uuid_str[3] = uuid.substring(16, 20); // 4-3
            uuid_str[4] = uuid.substring(20, 32); // 12
            for (String s : uuid_str) {
                sb.append(s);
                i++;
                if(i != 5) {
                    sb.append("-");
                }
            }
        } else throw new IllegalArgumentException("Failed to parse UUID");
        return sb.toString();
    }
    /**
     * undash makes UUID(dashed) sting to be non-dashed.
     * @param uuid UUID(dashed) String.
     * @return Returns 8-4-4-4-12 pattern UUID String
     * @throws IllegalArgumentException if uuid isn't dashed and not 36 char long.
     */
    public static String undash(String uuid) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if(uuid.length() == 36) {
            for (String s:uuid.split("-")) {
                sb.append(s);
            }
        } else throw new IllegalArgumentException("Failed to parse UUID");
        return sb.toString();
    }
}
