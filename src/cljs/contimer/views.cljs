(ns contimer.views
  (:require
    [re-frame.core :as re-frame]
    [contimer.subs :as subs]
    [contimer.watchface.views :refer [watchface]]))


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div {:style {:font-family :sans-serif
                   :height "100vh"
                   :display :flex
                   :flex-direction :column
                   :align-items :center
                   :justify-content :center}}
     [watchface]]))
