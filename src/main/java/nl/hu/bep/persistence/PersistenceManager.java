package nl.hu.bep.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.hu.bep.model.World;

import java.io.*;

public class PersistenceManager {

    private final static String ENDPOINT = "https://maikol01997.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=bfqt&srt=co&sp=rwdlacupx&se=2020-10-06T18:09:27Z&st=2020-07-20T10:09:27Z&spr=https&sig=nGHtOjOOxgdqU%2BiZkRHbTwKm%2F2T%2FSbXdYfgA12IZQTM%3D";
    private final static String CONTAINER = "worldcontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();

    public static void saveWorldToAzure() throws IOException {
        if (!blobContainer.exists()){
            blobContainer.create();
        }

        BlobClient blob = blobContainer.getBlobClient("world_blob");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(World.getWorld());

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        oos.close();
        bais.close();

    }

    public static void loadWorldFromAzure() throws IOException, ClassNotFoundException{
        System.out.println("Test0");
        if(blobContainer.exists()) {
            System.out.println("Test1");

            BlobClient blob = blobContainer.getBlobClient("world_blob");

            if (blob.exists()) {
                System.out.println("Test2");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                byte[] bytez = baos.toByteArray();

                ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
                ObjectInputStream ois = new ObjectInputStream(bais);

                Object obj = ois.readObject();
                if (obj instanceof World) {
                    System.out.println("Test3");
                    World loadedWorld = (World)obj;
                    World.setWorld(loadedWorld);
                }

                baos.close();
                ois.close();
            }
        }
    }
}

