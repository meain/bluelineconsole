package net.nhiroki.bluelineconsole.commands.help;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.nhiroki.bluelineconsole.BuildConfig;
import net.nhiroki.bluelineconsole.R;
import net.nhiroki.bluelineconsole.interfaces.CandidateEntry;
import net.nhiroki.bluelineconsole.interfaces.EventLauncher;

public class HelpCandidateEntry implements CandidateEntry {
    @Override
    public String getTitle() {
        return "help";
    }

    @Override
    public View getView(Context context) {
        LinearLayout ret = new LinearLayout(context);
        ret.setOrientation(LinearLayout.VERTICAL);

        TextView versionView = new TextView(context);
        versionView.setText(context.getString(R.string.app_name) + " " + BuildConfig.VERSION_NAME);
        versionView.setTypeface(Typeface.DEFAULT_BOLD);
        versionView.setTextSize(15);
        versionView.setTextColor(ContextCompat.getColor(context, R.color.baseText));
        ret.addView(versionView);

        String detailString = context.getString(R.string.app_help_text);
        TextView detailView = new TextView(context);
        detailView.setText(detailString);
        detailView.setTextSize(15);
        detailView.setTextColor(ContextCompat.getColor(context, R.color.baseText));
        detailView.setGravity(Gravity.LEFT); // Currently RTL not supported
        ret.addView(detailView);

        return ret;
    }

    @Override
    public EventLauncher getEventLauncher(Context context) {
        return null;
    }

    @Override
    public boolean hasEvent() {
        return false;
    }

    @Override
    public boolean hasLongView() {
        return true;
    }

    @Override
    public Drawable getIcon(Context context) {
        try {
            return context.getPackageManager().getApplicationIcon(BuildConfig.APPLICATION_ID);
        } catch (PackageManager.NameNotFoundException e) {
            // NameNotFoundException never happens as this function is just getting the icon for this app.
            throw new RuntimeException("NameNotFoundException when finding my icon.");
        }
    }
}
