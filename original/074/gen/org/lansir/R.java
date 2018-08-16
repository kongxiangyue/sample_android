/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package org.lansir;

public final class R {
    public static final class array {
        public static final int entries_list_preference=0x7f040000;
        public static final int entriesvalue_list_preference=0x7f040001;
    }
    public static final class attr {
        /**  The human-readable array to present as a list. Each entry must have a corresponding
             index in entryValues. 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int entries=0x7f010000;
        /**  The array to find the value to save for a preference when an entry from
             entries is selected. If a user clicks on the second item in entries, the
             second item in this array will be saved to the preference. 
         <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int entryValues=0x7f010001;
    }
    public static final class color {
        public static final int black=0x7f050001;
        public static final int dark_grey=0x7f050003;
        public static final int light_grey=0x7f050002;
        public static final int transparent=0x7f050004;
        public static final int white=0x7f050000;
    }
    public static final class drawable {
        public static final int bg_listitem=0x7f020000;
        public static final int bg_listitem_default=0x7f020001;
        public static final int bg_listitem_pressed=0x7f020002;
        public static final int bg_listitem_selected=0x7f020003;
        public static final int btn_radio_off=0x7f020004;
        public static final int btn_radio_off_pressed=0x7f020005;
        public static final int btn_radio_off_selected=0x7f020006;
        public static final int btn_radio_on=0x7f020007;
        public static final int btn_radio_on_pressed=0x7f020008;
        public static final int btn_radio_on_selected=0x7f020009;
        public static final int center=0x7f02000a;
        public static final int dialog=0x7f02000b;
        public static final int dialog_background=0x7f02000c;
        public static final int dialog_footer=0x7f02000d;
        public static final int dialog_title=0x7f02000e;
        public static final int footer=0x7f02000f;
        public static final int header=0x7f020010;
        public static final int ic_btn_round_more_normal=0x7f020011;
        public static final int icon=0x7f020012;
        public static final int title=0x7f020013;
    }
    public static final class id {
        public static final int content=0x7f080004;
        public static final int ctvListItem=0x7f080006;
        public static final int lsvTest=0x7f080002;
        public static final int lvListItem=0x7f080005;
        public static final int title=0x7f080003;
        public static final int tvListSelectLayoutContent=0x7f080001;
        public static final int tvListSelectLayoutTitle=0x7f080000;
    }
    public static final class layout {
        public static final int list_select_layout=0x7f030000;
        public static final int main=0x7f030001;
        public static final int single_selection_dialog=0x7f030002;
        public static final int single_selection_list_item=0x7f030003;
    }
    public static final class string {
        public static final int app_name=0x7f060000;
    }
    public static final class style {
        public static final int DialogText=0x7f070000;
        public static final int DialogText_Title=0x7f070001;
        /** 边框
         */
        public static final int Theme_Dialog_ListSelect=0x7f070002;
    }
    public static final class styleable {
        /** Attributes that can be used with a ListSelectView.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #ListSelectView_entries org.lansir:entries}</code></td><td> The human-readable array to present as a list.</td></tr>
           <tr><td><code>{@link #ListSelectView_entryValues org.lansir:entryValues}</code></td><td> The array to find the value to save for a preference when an entry from
             entries is selected.</td></tr>
           </table>
           @see #ListSelectView_entries
           @see #ListSelectView_entryValues
         */
        public static final int[] ListSelectView = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>
          @attr description
           The human-readable array to present as a list. Each entry must have a corresponding
             index in entryValues. 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name org.lansir:entries
        */
        public static final int ListSelectView_entries = 0;
        /**
          <p>
          @attr description
           The array to find the value to save for a preference when an entry from
             entries is selected. If a user clicks on the second item in entries, the
             second item in this array will be saved to the preference. 


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          <p>This is a private symbol.
          @attr name org.lansir:entryValues
        */
        public static final int ListSelectView_entryValues = 1;
    };
}
