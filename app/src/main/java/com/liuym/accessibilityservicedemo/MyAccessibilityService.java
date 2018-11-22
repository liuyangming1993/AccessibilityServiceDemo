package com.liuym.accessibilityservicedemo;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        CharSequence classNameChr = event.getClassName();
        String className = classNameChr.toString();
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                if (className.equals("android.widget.Button")) {
                    final List<CharSequence> text = event.getText();
                    for (CharSequence charSequence : text) {
                        if (charSequence.toString().equals("1")) {
                            clickBt2();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private void clickBt2() {
        AccessibilityNodeInfo info = getRootInActiveWindow();
        if (info != null) {
            List<AccessibilityNodeInfo> nodes = info.findAccessibilityNodeInfosByViewId("com.liuym.mydemotest:id/bt2");
            for (AccessibilityNodeInfo node : nodes) {
                if (node.getText().toString().equals("2")) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
