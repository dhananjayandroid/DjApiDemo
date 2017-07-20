package com.djay.demoapi.data;

import com.djay.demoapi.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * {@link Interceptor} class for mocking web-api response
 *
 * @author Dhananjay Kumar
 */
@SuppressWarnings("unused")
class FakeInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response;
        if (BuildConfig.DEBUG) {
            String responseString;
//        final HttpUrl url = chain.request().url();
//        final String query = uri.toString();
//        if (query.equalsIgnoreCase("id")) {
            responseString = FAKE_RESPONSE_1;
//        }
            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
            response = chain.proceed(chain.request());
        }

        return response;
    }

    // FAKE RESPONSES.
    private final static String FAKE_RESPONSE_1 = "{\"title\":\"Recipe Puppy\",\"version\":0.1," +
            "\"href\":\"http:\\/\\/www.recipepuppy.com\\/\",\"results\":[{\"title\":\"Fake Title 1 \"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Ginger-Champagne\\/Detail.aspx\"," +
            "\"ingredients\":\"champagne, ginger, ice, vodka\",\"thumbnail\":\"http:\\/\\/img.recipepuppy" +
            ".com\\/1.jpg\"},{\"title\":\"Fake Title 2\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Potato-and-Cheese-Frittata\\/Detail.aspx\",\"ingredients\":\"cheddar cheese, eggs, olive" +
            " oil, onions, potato, salt\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/2.jpg\"},{\"title\":\"Fake" +
            " Title 3\",\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Eggnog-Thumbprints\\/Detail.aspx\"," +
            "\"ingredients\":\"brown sugar, butter, butter, powdered sugar, eggs, flour, nutmeg, rum, salt, vanilla " +
            "extract, sugar\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/3.jpg\"},{\"title\":\"Fake Title 4\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Succulent-Pork-Roast\\/Detail.aspx\"," +
            "\"ingredients\":\"brown sugar, garlic, pork chops, water\",\"thumbnail\":\"http:\\/\\/img.recipepuppy" +
            ".com\\/4.jpg\"},{\"title\":\"Fake Title 5\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Irish-Champ\\/Detail.aspx\",\"ingredients\":\"black pepper, butter, green onion, milk, " +
            "potato, salt\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/5.jpg\"},{\"title\":\"Fake Title 6\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Chocolate-Cherry-Thumbprints\\/Detail.aspx\"," +
            "\"ingredients\":\"cocoa powder, baking powder, butter, eggs, flour, oats, salt, sugar, vanilla " +
            "extract\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/6.jpg\"},{\"title\":\"Fake Title 6\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Mean-Woman-Pasta\\/Detail.aspx\"," +
            "\"ingredients\":\"garlic, kalamata olive, olive oil, pepperoncini, seashell pasta, tomato\"," +
            "\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/7.jpg\"},{\"title\":\"Fake Title 7\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Hot-Spiced-Cider\\/Detail.aspx\"," +
            "\"ingredients\":\"allspice, apple cider, brown sugar, cinnamon, cloves, nutmeg, orange, salt\"," +
            "\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/8.jpg\"},{\"title\":\"Fake Title 7\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Isas-Cola-de-Mono\\/Detail.aspx\"," +
            "\"ingredients\":\"cinnamon, cloves, instant coffee, milk, rum, vanilla extract, water, sugar\"," +
            "\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/9.jpg\"},{\"title\":\"Fake Title 8\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Amys-Barbecue-Chicken-Salad\\/Detail.aspx\"," +
            "\"ingredients\":\"barbecue sauce, chicken, cilantro, lettuce, ranch dressing, lettuce, tomato\"," +
            "\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/10.jpg\"}]}";
    private final static String FAKE_RESPONSE_2 = "{\"title\":\"Recipe Puppy\",\"version\":0.1," +
            "\"href\":\"http:\\/\\/www.recipepuppy.com\\/\",\"results\":[{\"title\":\"Ginger Champagne\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Ginger-Champagne\\/Detail.aspx\"," +
            "\"ingredients\":\"champagne, ginger, ice, vodka\",\"thumbnail\":\"http:\\/\\/img.recipepuppy" +
            ".com\\/1.jpg\"},{\"title\":\"Potato and Cheese Frittata\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Potato-and-Cheese-Frittata\\/Detail.aspx\",\"ingredients\":\"cheddar cheese, eggs, olive" +
            " oil, onions, potato, salt\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/2.jpg\"}," +
            "{\"title\":\"Eggnog Thumbprints\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Eggnog-Thumbprints\\/Detail.aspx\",\"ingredients\":\"brown sugar, butter, butter, " +
            "powdered sugar, eggs, flour, nutmeg, rum, salt, vanilla extract, sugar\",\"thumbnail\":\"http:\\/\\/img" +
            ".recipepuppy.com\\/3.jpg\"},{\"title\":\"Succulent Pork Roast\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Succulent-Pork-Roast\\/Detail.aspx\",\"ingredients\":\"brown sugar, garlic, pork chops, " +
            "water\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/4.jpg\"},{\"title\":\"Irish Champ\"," +
            "\"href\":\"http:\\/\\/allrecipes.com\\/Recipe\\/Irish-Champ\\/Detail.aspx\",\"ingredients\":\"black " +
            "pepper, butter, green onion, milk, potato, salt\",\"thumbnail\":\"http:\\/\\/img.recipepuppy" +
            ".com\\/5.jpg\"},{\"title\":\"Chocolate-Cherry Thumbprints\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Chocolate-Cherry-Thumbprints\\/Detail.aspx\",\"ingredients\":\"cocoa powder, baking " +
            "powder, butter, eggs, flour, oats, salt, sugar, vanilla extract\",\"thumbnail\":\"http:\\/\\/img" +
            ".recipepuppy.com\\/6.jpg\"},{\"title\":\"Mean Woman Pasta\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Mean-Woman-Pasta\\/Detail.aspx\",\"ingredients\":\"garlic, kalamata olive, olive oil, " +
            "pepperoncini, seashell pasta, tomato\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/7.jpg\"}," +
            "{\"title\":\"Hot Spiced Cider\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Hot-Spiced-Cider\\/Detail.aspx\",\"ingredients\":\"allspice, apple cider, brown sugar, " +
            "cinnamon, cloves, nutmeg, orange, salt\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/8.jpg\"}," +
            "{\"title\":\"Isa's Cola de Mono\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Isas-Cola-de-Mono\\/Detail.aspx\",\"ingredients\":\"cinnamon, cloves, instant coffee, " +
            "milk, rum, vanilla extract, water, sugar\",\"thumbnail\":\"http:\\/\\/img.recipepuppy.com\\/9.jpg\"}," +
            "{\"title\":\"Amy's Barbecue Chicken Salad\",\"href\":\"http:\\/\\/allrecipes" +
            ".com\\/Recipe\\/Amys-Barbecue-Chicken-Salad\\/Detail.aspx\",\"ingredients\":\"barbecue sauce, chicken, " +
            "cilantro, lettuce, ranch dressing, lettuce, tomato\",\"thumbnail\":\"http:\\/\\/img.recipepuppy" +
            ".com\\/10.jpg\"}]}";

}