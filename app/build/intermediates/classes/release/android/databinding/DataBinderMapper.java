
package android.databinding;
import com.tixon.timemanagement.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 17;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
            case com.tixon.timemanagement.R.layout.task_list_categorized_item:
                return com.tixon.timemanagement.databinding.TaskListCategorizedItemBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.fragment_tasks:
                return com.tixon.timemanagement.databinding.FragmentTasksBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.task_item:
                return com.tixon.timemanagement.databinding.TaskItemBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.activity_settings:
                return com.tixon.timemanagement.databinding.ActivitySettingsBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.task_list_item:
                return com.tixon.timemanagement.databinding.TaskListItemBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.activity_task_description:
                return com.tixon.timemanagement.databinding.ActivityTaskDescriptionBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.activity_create_task:
                return com.tixon.timemanagement.databinding.ActivityCreateTaskBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.fragment_eisenhower:
                return com.tixon.timemanagement.databinding.FragmentEisenhowerBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.fragment_all_tasks:
                return com.tixon.timemanagement.databinding.FragmentAllTasksBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.fragment_incomes_expenses:
                return com.tixon.timemanagement.databinding.FragmentIncomesExpensesBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.task_list_activity:
                return com.tixon.timemanagement.databinding.TaskListActivityBinding.bind(view, bindingComponent);
            case com.tixon.timemanagement.R.layout.activity_main:
                return com.tixon.timemanagement.databinding.ActivityMainBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1108562724:
                if(tag.equals("layout/task_list_categorized_item_0"))
                    return com.tixon.timemanagement.R.layout.task_list_categorized_item;
                break;
            case 1428968725:
                if(tag.equals("layout/fragment_tasks_0"))
                    return com.tixon.timemanagement.R.layout.fragment_tasks;
                break;
            case 836136601:
                if(tag.equals("layout/task_item_0"))
                    return com.tixon.timemanagement.R.layout.task_item;
                break;
            case -415786017:
                if(tag.equals("layout/activity_settings_0"))
                    return com.tixon.timemanagement.R.layout.activity_settings;
                break;
            case -665403056:
                if(tag.equals("layout/task_list_item_0"))
                    return com.tixon.timemanagement.R.layout.task_list_item;
                break;
            case 843927870:
                if(tag.equals("layout/activity_task_description_0"))
                    return com.tixon.timemanagement.R.layout.activity_task_description;
                break;
            case -1274776594:
                if(tag.equals("layout/activity_create_task_0"))
                    return com.tixon.timemanagement.R.layout.activity_create_task;
                break;
            case 925429152:
                if(tag.equals("layout/fragment_eisenhower_0"))
                    return com.tixon.timemanagement.R.layout.fragment_eisenhower;
                break;
            case 1343596919:
                if(tag.equals("layout/fragment_all_tasks_0"))
                    return com.tixon.timemanagement.R.layout.fragment_all_tasks;
                break;
            case 240546923:
                if(tag.equals("layout/fragment_incomes_expenses_0"))
                    return com.tixon.timemanagement.R.layout.fragment_incomes_expenses;
                break;
            case 1406182476:
                if(tag.equals("layout/task_list_activity_0"))
                    return com.tixon.timemanagement.R.layout.task_list_activity;
                break;
            case 423753077:
                if(tag.equals("layout/activity_main_0"))
                    return com.tixon.timemanagement.R.layout.activity_main;
                break;
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"task"};
    }
}