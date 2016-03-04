package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class ActivityTaskDescriptionBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbarTaskDescription, 5);
        sViewsWithIds.put(R.id.taskDescriptionLayout, 6);
        sViewsWithIds.put(R.id.taskDescriptionDateTimeLayout, 7);
        sViewsWithIds.put(R.id.fabDescription, 8);
    }
    // views
    public final android.support.design.widget.FloatingActionButton fabDescription;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    public final android.widget.LinearLayout taskDescriptionDateTimeLayout;
    public final android.widget.LinearLayout taskDescriptionLayout;
    public final android.widget.EditText tdDate;
    public final android.widget.EditText tdDescription;
    public final android.widget.EditText tdTime;
    public final android.widget.EditText tdTitle;
    public final android.support.v7.widget.Toolbar toolbarTaskDescription;
    // variables
    private com.tixon.timemanagement.task.Task mTask;
    // values
    // listeners
    
    public ActivityTaskDescriptionBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.fabDescription = (android.support.design.widget.FloatingActionButton) bindings[8];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.taskDescriptionDateTimeLayout = (android.widget.LinearLayout) bindings[7];
        this.taskDescriptionLayout = (android.widget.LinearLayout) bindings[6];
        this.tdDate = (android.widget.EditText) bindings[3];
        this.tdDate.setTag(null);
        this.tdDescription = (android.widget.EditText) bindings[2];
        this.tdDescription.setTag(null);
        this.tdTime = (android.widget.EditText) bindings[4];
        this.tdTime.setTag(null);
        this.tdTitle = (android.widget.EditText) bindings[1];
        this.tdTitle.setTag(null);
        this.toolbarTaskDescription = (android.support.v7.widget.Toolbar) bindings[5];
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
                // read description~.~task~
                descriptionTask = task.getDescription();
                // read title~.~task~
                titleTask = task.getTitle();
            }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1
            this.tdDate.setText(dateTask);
            this.tdDescription.setText(descriptionTask);
            this.tdTime.setText(timeTask);
            this.tdTitle.setText(titleTask);
        }
    }
    // Listener Stub Implementations
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    
    public static ActivityTaskDescriptionBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityTaskDescriptionBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityTaskDescriptionBinding>inflate(inflater, com.tixon.timemanagement.R.layout.activity_task_description, root, attachToRoot, bindingComponent);
    }
    public static ActivityTaskDescriptionBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityTaskDescriptionBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.activity_task_description, null, false), bindingComponent);
    }
    public static ActivityTaskDescriptionBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityTaskDescriptionBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_task_description_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityTaskDescriptionBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: task~
        flag 1: INVALIDATE ANY
    flag mapping end*/
    //end