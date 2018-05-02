
![Curamatik Logo](img/curamatik_logo.png)
# Disclaimer Views

Simple drop-in views for showing disclaimer and other user notices.
Primary and accent colors of the current style are automatically applied. 

## Decision View

Shows text to the user and provides two control buttons.

![Screenshot Decision View](img/screenshot_1.png)

## Toggle View

Shows text to the user, provides a toggle interaction and two different buttons state depending on the state of the button.

![Screenshot Decision View](img/screenshot_2.png)


# Usage

Add the JitPack repository to your build file

```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add dependency to build.gradle

```
dependencies {
    implementation "com.github.curamatik:disclaimer-views:[latest-version]"
}
```

Add view(s) to layout file:

## Decision View

```
    <de.curamatik.library.disclaimerviews.DecisionView
        android:id="@+id/decision_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:pv_body="@string/decision_view_sample_lorem_ipsum"
        app:pv_indicator_enabled="true"
        app:pv_interaction_enabled="true"
        app:pv_negative_button_text="@string/decision_view_sample_negative_button"
        app:pv_positive_button_text="@string/decision_view_sample_positive_button"
        app:pv_title="@string/decision_view_sample_title"
        />

```

__pv_title__ [String]  
Title text, when the title is not set, the textViews visibility is set GONE.

__pv_body__ [String]  
Body text to be shown below the title.

__pv_negative_button_text__ [String]  
The text of the left button.

__pv_positive_button_text__ [String]  
The text of the right button.

__pv_indicator_enabled__ [Boolean]  
Whether the scrolling indicator is shown. The indicator automatically hides when the user has reached the bottom of the scroll view. 

__pv_interaction_enabled__ [Boolean]  
When set to false, the buttons at the bottom of the page are not shown.

### Listener

```java
DecisionView decisionView = rootView.findViewById(R.id.decision_view);
decisionView.setPositiveButtonListener(v -> {
    //positive (right) button action
    });
decisionView.setNegativeButtonListener(v -> {
    //negative (left) button action
});
```

## Toggle View

```
    <de.curamatik.library.disclaimerviews.ToggleView
        android:id="@+id/toggle_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:pv_body="@string/decision_view_sample_lorem_ipsum"
        app:pv_continue_button_text="@string/decision_view_sample_continue_button"
        app:pv_indicator_enabled="true"
        app:pv_interaction_enabled="true"
        app:pv_skip_button_text="@string/decision_view_sample_skip_button"
        app:pv_title="@string/decision_view_sample_title"
        app:pv_toggle_on_text="@string/decision_view_sample_toggle_on_text"
        app:pv_toggle_off_text="@string/decision_view_sample_toggle_off_text"
        />

```

__pv_title__ [String]  
Title text, when the title is not set, the textViews visibility is set GONE.

__pv_body__ [String]  
Body text to be shown below the title.

__pv_continue_button_text__ [String]  
The text of the continue button, shown when the toggle is ON.

__pv_toggle_on_text__ [String]  
Toggle description shown when the toggle is ON.

__pv_skip_button_text__ [String]  
The text of the skip button, shown when the toggle is OFF.

__pv_toggle_off_text__ [String]  
Toggle description shown when the toggle is OFF.

__pv_continue_button_text__ [String]  
The text of the continue button, shown when the toggle is ON.

__pv_indicator_enabled__ [Boolean]  
Whether the scrolling indicator is shown. The indicator automatically hides when the user has reached the bottom of the scroll view. 

__pv_interaction_enabled__ [Boolean]  
When set to false, the buttons at the bottom of the page are not shown.

### Listener

```java
ToggleView toggleView = rootView.findViewById(R.id.toggle_view);
decisionView.setSkipButtonListener(v -> {
    //skip button action
    });
decisionView.setContinueButtonListener(v -> {
    //continue button action
});
```

# License
-------

    Copyright 2018 Curamatik UG

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



