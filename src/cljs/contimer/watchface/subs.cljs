(ns contimer.watchface.subs
  (:require [re-frame.core :as re]
            [cljs-time.core :as t]
            [cljs-time.format :as tf]
            [cljs-time.periodic :as p]))

(def minute-formatter (tf/formatter "mm"))

(re/reg-sub
  ::time
  (fn [{:keys [time]}]
    (let [minutes (t/in-minutes (t/interval (t/now) (t/from-now (t/hours 0.1))))]
      (str minutes ":" (:seconds time)))))

(re/reg-sub
  ::started?
  (fn [{:keys [time]}]
    (:started? time)))