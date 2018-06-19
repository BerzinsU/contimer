(ns contimer.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [contimer.events :as events]
    [contimer.views :as views]
    [contimer.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))

;; https://github.com/Day8/re-frame/blob/master/docs/FAQs/PollADatabaseEvery60.md
(re-frame.core/reg-fx                                       ;; the re-frame API for registering effect handlers
  :interval                                                 ;; the effect id
  (let [live-intervals (atom {})]                           ;; storage for live intervals
    (fn [{:keys [action id frequency event]}]               ;; the handler
      (if (= action :start)
        (swap! live-intervals assoc id (js/setInterval #(re-frame/dispatch event) frequency))
        (do (js/clearInterval (get @live-intervals id))
            (swap! live-intervals dissoc id))))))