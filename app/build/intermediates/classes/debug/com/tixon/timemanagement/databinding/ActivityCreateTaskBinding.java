package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class ActivityCreateTaskBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbarCreateTask, 6);
        sViewsWithIds.put(R.id.buttonCreateTask, 7);
    }
    // views
    public final android.widget.Button buttonCreateTask;
    public final android.widget.CheckBox checkBoxIsImportantTask;
    public final android.widget.EditText editTextDate;
    public final android.widget.EditText editTextDescription;
    public final android.widget.EditText editTextTime;
    public final android.widget.EditText editTextTitle;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    public final android.support.v7.widget.Toolbar toolbarCreateTask;
    // variables
    private com.tixon.timemanagement.task.Task mTask;
    // values
    // listeners
    
    public ActivityCreateTaskBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.buttonCreateTask = (android.widget.Button) bindings[7];
        this.checkBoxIsImportantTask = (android.widget.CheckBox) bindings[5];
        this.checkBoxIsImportantTask.setTag(null);
        this.editTextDate = (android.widget.EditText) bindings[3];
        this.editTextDate.setTag(null);
        this.editTextDescription = (android.widget.EditText) bindings[2];
        this.editTextDescription.setTag(null);
        this.editTextTime = (android.widget.EditText) bindings[4];
        this.editTextTime.setTag(null);
        this.editTextTitle = (android.widget.EditText) bindings[1];
        this.editTextTitle.setTag(null);
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbarCreateTask = (android.support.v7.widget.Toolbar) bindings[6];
        setRootTag(root);
        invalidateAll();
    }
    
    @Override
    public void invalidateAll() {
        synchronized(this) {
            mDirtyFlags = 0x2L;
        }
        requestRebind();
    }
    
    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.task :
                setTask((com.tixon.timemanagement.task.Task) variable);
                return true;
        }
        return false;
    }
    
    public void setTask(com.tixon.timemanagement.task.Task task) {
        this.mTask = task;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        super.requestRebind();
    }
    public com.tixon.timemanagement.task.Task getTask() {
        return mTask;
    }
    
    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }
    
    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String timeTask = null;
        java.lang.String dateTask = null;
        boolean importantTask = false;
        java.lang.String descriptionTask = null;
        java.lang.String titleTask = null;
        com.tixon.timemanagement.task.Task task = mTask;
    
        if ((dirtyFlags & 0x3L) != 0) {
            // read task~
            task = task;
        
            if (task != null) {
                // read time~.~task~
                timeTask = task.getTime();
                // read date~.~task~
                dateTask = task.getDate();
                // read important~.~task~
                importantTask = task.isImportant();
                // read description~.~task~
                descriptionTask = task.getDescription();
                // read title~.~task~
                titleTask = task.getTitle();
            }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1
            this.checkBoxIsImportantTask.setChecked(importantTask);
            this.editTextDate.setText(dateTask);
            this.editTextDescription.setText(descriptionTask);
            this.editTextTime.setText(timeTask);
            this.editTextTitle.setText(titleTask);
        }
    }
    // Listener Stub Implementations
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    
    public static ActivityCreateTaskBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityCreateTaskBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityCreateTaskBinding>inflate(inflater, com.tixon.timemanagement.R.layout.activity_create_task, root, attachToRoot, bindingComponent);
    }
    public static ActivityCreateTaskBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityCreateTaskBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.activity_create_task, null, false), bindingComponent);
    }
    public static ActivityCreateTaskBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityCreateTaskBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_create_task_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityCreateTaskBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: task~
        flag 1: INVALIDATE ANY
    flag mapping end*/
    //end