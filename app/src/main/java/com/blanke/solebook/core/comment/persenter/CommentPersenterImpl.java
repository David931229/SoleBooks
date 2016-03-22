package com.blanke.solebook.core.comment.persenter;

import com.avos.avoscloud.AVQuery;
import com.blanke.solebook.bean.Book;
import com.blanke.solebook.bean.SoleUser;
import com.blanke.solebook.rx.RxBookComment;


/**
 * Created by Blanke on 16-3-22.
 */
public class CommentPersenterImpl extends CommentPersenter {

    private boolean pullToRefresh;

    @Override
    public void getBookCommentData(Book book, boolean pullToRefresh, int skip, int limit) {
        this.pullToRefresh = pullToRefresh;
        getView().showLoading(pullToRefresh);
        RxBookComment.getBookCommentListData(
                book, AVQuery.CachePolicy.NETWORK_ELSE_CACHE
                , limit, skip).subscribe(this::onSuccess, this::onFail);
    }

    @Override
    public void sendBookComment(Book book, String content) {
        if (getView() == null) {
            return;
        }
        SoleUser user = SoleUser.getCurrentUser(SoleUser.class);
        if (user == null || user.isAnonymous()) {
            return;
        }
        RxBookComment.sendBookComment(book, content, user)
                .subscribe(bookComments -> getView().sendSuccess()
                        , this::onFail);
    }

    @Override
    public boolean getPullToRefresh() {
        return pullToRefresh;
    }
}