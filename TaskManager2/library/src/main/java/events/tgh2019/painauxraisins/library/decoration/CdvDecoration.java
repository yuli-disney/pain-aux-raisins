package events.tgh2019.painauxraisins.library.decoration;


import android.graphics.Rect;
import events.tgh2019.painauxraisins.library.DayView;
import events.tgh2019.painauxraisins.library.PopupView;
import events.tgh2019.painauxraisins.library.EventView;
import events.tgh2019.painauxraisins.library.data.IEvent;
import events.tgh2019.painauxraisins.library.data.IPopup;

/**
 * Created by FRAMGIA\pham.van.khac on 22/07/2016.
 */
public interface CdvDecoration {

    EventView getEventView(IEvent event, Rect eventBound, int hourHeight, int seperateHeight);

    PopupView getPopupView(IPopup popup, Rect eventBound, int hourHeight, int seperateHeight);

    DayView getDayView(int hour);
}
