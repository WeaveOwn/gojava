package com.weaveown;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author wangwei
 * @date 2021/12/17
 */
public class XLYUtil {

    public static void main(String[] args) throws Exception {
        decrypt();
        encrypt();

    }

    private static void encrypt() throws Exception {
        String inputStr = "{\"ClientPriKey\":\"45c13ccd7a990428157f4ef0598e2f9e6c1466ccf9524772c9a8783d2ba6bfdf\",\"ClientPubKey\":\"045664d406af68385cac1c93c20e279ddacc451d265a72bd01420d0bafd356a730e04250ecd776a4ead6aa2ce29b9410b49d4234b970c1e6282fb1558b2c9e331b\",\"PlatFormPubKey\":\"04b13309c978a4e069672d887881dcf50e355f09a913ec5cd16dce10bfe97496ee11385290ee0142f03d56779fe4d1e090ed5010c3450ff3c88fa75f5b6916bc87\",\"Items\":[{\"TaxNo\":\"\",\"DiskNo\":\"\",\"UserPriKey\":\"\",\"UserPubKey\":\"\"}]}";
        //KEY转换
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
        final byte[] result = cipher.doFinal(inputStr.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString().toUpperCase());
    }


    private static void decrypt() throws Exception {
        String str =
                "2090088F7449B367CDF0C3B9230DBD481CFD4BC1282EF2E288A09C2AAB1D7FD65A0D1B3814FBAF08F7E6A263CE72F34DE2869BB04D6AD45C161C7072686155EB4156759AF9C9B8DDEFFBC546D7838302BBAD7F8A9BC36540E3C0C5A00D6E53E48474EB5EB51B3DDF2B98615697BE584669C45F38425482221634CE38534DCA16E932F92F15D5B9CA1A689EA81315940CAB4CC02D72979FD3BFBAA4BC7EF418B5A220A227FC1D871A47F704D903C69ACBC79F32446FF5244071AA661C1E5015BB18FFAD703ADAFD4116E28A6AF1D09B2E323E716EC81403838A3ADE077505D205320DD199F7E5BC458AA342BDB8B2BFB026C3732DD6BB1270A733F579ACFAC9EEAFA8C750707649DEDE272886B40F324AB6498032FC6DF4B00883A9B0E3A135409A2F63393ADB2D49182FCFFDA836779A47BA8EA25341CB8AADBD0EDE9D83063376071FFA0C845007AC954B474FEF2D5D8623B7B04FCFA34979927DA985A44BD4D4DA9C645CD5AC140CF095E02C2D9DEE854E61F05568CE753B31728E33ED91F474FD19366800849C04446926C1621B2DF59A200F3FF00926141F2D98CEBB557E904CC344BD7271E50435A38ADC2A8D46C31BD20AF513408BF6ECD469BAFE4D4F6E4D6E00706F9ACE2D282A43FAD211AD091C9F40B05094A81BFE5314AE574ECC9D8387B9F8633E98A97D7A0D2C0F89599DC6A13725753D00B1F4559DC6AF15F9EAED9856F194CED8D994C8B98FE2F9E3743706AF2239C102A7C7F31B1F82C395C1AC809FDF70D345588F6A0EBC81FF5840B148CC4ACCAF57FE3BCDBB78B3765A3E22EDC43BEEB8902D8505AF3D041B94D8988C9F773252E0\n";
        byte[] inputBytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int x = Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            inputBytes[i] = (byte) x;
        }

        //KEY转换
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
        final byte[] result = cipher.doFinal(inputBytes);
        System.out.println(new String(result));
    }

    private static Cipher getCipher(int encryptMode) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        DESKeySpec desKeySpec = new DESKeySpec("d4xTCGL7".getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        Key convertSecretKey = factory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("d4xTCGL7".getBytes(StandardCharsets.UTF_8));
        cipher.init(encryptMode, convertSecretKey, iv);
        return cipher;
    }


}
