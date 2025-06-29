package tech.buildrun.springsecurity.keys;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RsaKeyGenerator {

    public static void generateAndSaveKeys(String privateKeyPath, String publicKeyPath) throws IOException {
        // Garante que o diretório existe
        Path privateKeyDir = Paths.get(privateKeyPath).getParent();
        if (!Files.exists(privateKeyDir)) {
            Files.createDirectories(privateKeyDir);
        }

        Path publicKeyDir = Paths.get(publicKeyPath).getParent();
        if (!Files.exists(publicKeyDir)) {
            Files.createDirectories(publicKeyDir);
        }

        try {
            // Gera o par de chaves RSA 2048 bits
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();

            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            // Codifica as chaves em Base64 (formato PEM simplificado)
            String privateKeyPem = "-----BEGIN PRIVATE KEY-----\n" +
                    encodeKey(privateKey.getEncoded()) +
                    "-----END PRIVATE KEY-----\n";

            String publicKeyPem = "-----BEGIN PUBLIC KEY-----\n" +
                    encodeKey(publicKey.getEncoded()) +
                    "-----END PUBLIC KEY-----\n";

            // Escreve os arquivos
            Files.write(Paths.get(privateKeyPath), privateKeyPem.getBytes());
            Files.write(Paths.get(publicKeyPath), publicKeyPem.getBytes());

            System.out.println("Chaves RSA geradas e salvas em:");
            System.out.println("Private Key: " + privateKeyPath);
            System.out.println("Public Key: " + publicKeyPath);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Erro ao gerar as chaves RSA", e);
        }
    }

    private static String encodeKey(byte[] keyBytes) {
        String encoded = Base64.getEncoder().encodeToString(keyBytes);
        // Quebra em linhas de 64 caracteres para o PEM ficar bonitinho
        return encoded.replaceAll("(.{64})", "$1\n") + "\n";
    }

    public static void main(String[] args) throws Exception {
        generateAndSaveKeys(
                "C:\\Users\\Inaq\\Desktop\\springboot - projetos\\springsecurity\\src\\main\\resources\\keys\\private.pem",
                "C:\\Users\\Inaq\\Desktop\\springboot - projetos\\springsecurity\\src\\main\\resources\\keys\\public.pem"
        );
    }
}
