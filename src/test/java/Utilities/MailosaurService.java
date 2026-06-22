package Utilities;

import com.mailosaur.MailosaurClient;
import com.mailosaur.models.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailosaurService {

    private final MailosaurClient client;
    private final String serverId;

    public MailosaurService(String apiKey, String serverId) {
        this.client = new MailosaurClient(apiKey);
        this.serverId = serverId;
    }

    public String getOtp(String email) throws Exception {

        MessageSearchParams params = new MessageSearchParams();
        params.withServer(serverId);

        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentTo(email);

        Message message = client.messages().get(params, criteria);

        String body = message.text().body();

        return extractOtp(body);
    }

    private String extractOtp(String body) {

        Pattern pattern = Pattern.compile("\\b(\\d{6})\\b");

        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new RuntimeException("OTP not found");
    }
}
