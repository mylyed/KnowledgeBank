package whimsicalgl.knowledgebank.ui.fragment;

import android.app.Fragment;
import android.widget.Toast;

/**
 * 题目的基类
 */
public abstract class TopicBaseFragment extends Fragment implements TopicFunction {
    Toast toast;

    protected void showMessage(String msg) {
        if (null != toast) {
            toast.cancel();
        }
        toast = Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
