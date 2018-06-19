(ns contimer.watchface.views
  (:require [re-frame.core :as re]
            [contimer.watchface.subs :as subs]
            [contimer.watchface.events :as ev]))

(defn timer []
  (let [time (re/subscribe [::subs/time])]
    [:div {:style {:font-weight :bold
                   :font-size   "20em"}}
     @time]))

(defn start-button []
  [:button {:style    {:font-size "3em"}
            :on-click #(re/dispatch [::ev/start-timer])}
   "START"])

(defn stop-button []
  [:button {:style    {:font-size "3em"}
            :on-click #(re/dispatch [::ev/stop-timer])}
   "STOP"])

(defn watchface []
  (let [started? @(re/subscribe [::subs/started?])]
    [:div {:style {:display        :flex
                   :flex-direction :column}}
     [timer]
     (if started?
       [stop-button]
       [start-button])]))